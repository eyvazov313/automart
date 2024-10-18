package az.atl.auto_mart.config.security;

import az.atl.auto_mart.entity.Admin;
import az.atl.auto_mart.entity.User;
import az.atl.auto_mart.exceptions.ErrorMessage;
import az.atl.auto_mart.exceptions.UserNotFoundException;
import az.atl.auto_mart.repository.AdminRepository;
import az.atl.auto_mart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    private final AdminRepository adminRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {

            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent()) return (UserDetails) user.get();

            Optional<Admin> admin = adminRepository.findByEmail(email);
            if (admin.isPresent()) return (UserDetails) admin.get();

            throw new UsernameNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage());
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
