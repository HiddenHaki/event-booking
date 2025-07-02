package com.harsh.event_ticketing.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().permitAll() // allow everything
                )
                .csrf(csrf -> csrf.disable()) // disable CSRF protection for now
                .formLogin(form -> form.disable()) // disable the login page
            
                .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }
}
