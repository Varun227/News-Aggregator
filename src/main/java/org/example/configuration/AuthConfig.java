package org.example.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        authorizeRequests->authorizeRequests.requestMatchers("/register")
                                .permitAll() // Allow access to authentication endpoints
                                .anyRequest()
                                .authenticated())
        .formLogin(formLogin->formLogin.defaultSuccessUrl("/hello",true).permitAll());
        return httpSecurity.build();// Require authentication for all other requests

        // Require authentication for all other requests
        // Configure your security filter chain here
         // Replace with actual configuration
    }

    // Other security-related beans and configurations can be added here
}
