package az.atl.auto_mart.service;

import az.atl.auto_mart.config.security.JwtService;
import az.atl.auto_mart.dto.AuthenticationResponse;
import az.atl.auto_mart.dto.LoginRequest;
import az.atl.auto_mart.dto.UserRegisterRequest;
import az.atl.auto_mart.entity.UUIDToken;
import az.atl.auto_mart.entity.User;
import az.atl.auto_mart.exceptions.*;
import az.atl.auto_mart.mapper.UserMapper;
import az.atl.auto_mart.repository.UUIDTokenRepository;
import az.atl.auto_mart.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final EmailService emailService;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final UUIDTokenRepository UUIDTokenRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;



    public void registerForUser(UserRegisterRequest request) {

        var userEntityOpt = userRepository.findByEmail(request.getEmail());

        if (userEntityOpt.isEmpty()) {
            var user = userMapper.dtoToEntity(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            var uuidToken = generateUUIDTokenForUserRegister(user);
            userRepository.save(user);
            UUIDTokenRepository.save(uuidToken);
            emailService.sendRegistrationLink(user, uuidToken.getToken());
            return;
        }

        var user = userEntityOpt.get();

        if (user.isEnabled()) {
            throw new UserAlreadyExistException();
        }

        var uuidTokenOpt = UUIDTokenRepository.findByUserId(user.getId());

        if (uuidTokenOpt.isEmpty()) {
            userMapper.updateUserEntity(request, user);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            var uuidToken = generateUUIDTokenForUserRegister(user);
            userRepository.save(user);
            UUIDTokenRepository.save(uuidToken);
            emailService.sendRegistrationLink(user, uuidToken.getToken());
            return;
        }

        var uuidToken = uuidTokenOpt.get();
        if (uuidToken.getExpiryDate().isAfter(LocalDateTime.now())) {
            emailService.sendRegistrationLink(user, uuidToken.getToken());
        } else {
            userMapper.updateUserEntity(request, user);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            updateUUIDToken(uuidToken);
            userRepository.save(user);
            UUIDTokenRepository.save(uuidToken);
            emailService.sendRegistrationLink(user, uuidToken.getToken());
        }
    }

    public void verifyRegisterForUser(String token) {

        UUIDToken uuidToken = UUIDTokenRepository.findByToken(token)
                .orElseThrow(TokenNotFoundException::new);

        if (uuidToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException();
        }
        var user = uuidToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        UUIDTokenRepository.delete(uuidToken);
    }

    public AuthenticationResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        var accessToken = jwtService.generateAccessToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public void sendForgotPasswordEmail(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(
                UserNotFoundException::new);

        UUIDTokenRepository.deleteUUIDTokenByUserId(user.getId());

        String token = UUID.randomUUID().toString();
        var passToken = UUIDToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusMinutes(30))// Token expires in 30 minutes
                .build();

        UUIDTokenRepository.save(passToken);
        emailService.sendForgotPasswordLink(user, token);
    }

    public void updatePassword(String token, String newPassword) {

        UUIDToken uuidToken = UUIDTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (uuidToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        var user = uuidToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void renewAccessToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String refreshToken;
        final String email;
        final String typeToken;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        email = jwtService.extractEmail(refreshToken);
        typeToken = jwtService.extractTokenType(refreshToken);

        if (email != null) {
            var userDetails = this.userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

            if ("REFRESH".equals(typeToken) && jwtService.isTokenValid(refreshToken, userDetails)) {
                var accessToken = jwtService.generateAccessToken(userDetails);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                response.setContentType(APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
            response.setStatus(BAD_REQUEST.value());
        }
    }

    private UUIDToken generateUUIDTokenForUserRegister(User user) {
        String token = UUID.randomUUID().toString();
        return UUIDToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusHours(1))
                .build();
    }

    private void updateUUIDToken(UUIDToken uuidToken) {
        String token = UUID.randomUUID().toString();
        uuidToken.setToken(token);
        uuidToken.setExpiryDate(LocalDateTime.now().plusMinutes(1));
    }
}
