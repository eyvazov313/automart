package az.atl.auto_mart.controller;

import az.atl.auto_mart.dto.AuthenticationResponse;
import az.atl.auto_mart.dto.UpdateUserPasswordRequest;
import az.atl.auto_mart.dto.LoginRequest;
import az.atl.auto_mart.dto.UserRegisterRequest;
import az.atl.auto_mart.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Validated
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        authenticationService.registerForUser(userRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/verify-register")
    public ResponseEntity<Void> verifyRegisterUser(@RequestParam String token){
        authenticationService.verifyRegisterForUser(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @PostMapping("/token")
    public void renewAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.renewAccessToken(request, response);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestParam String email) {
        authenticationService.sendForgotPasswordEmail(email);
    }

    @PatchMapping("/update-password")
    public void updatePassword(@RequestParam String token, @Valid @RequestBody UpdateUserPasswordRequest request) {
        authenticationService.updatePassword(token, request.getNewPassword());
    }
}













