package com.leoni.execution.contrats.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll()  // 🔓 Autorise l'accès à l'API sans auth
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()  // ❌ Désactive la redirection vers /login HTML
                .build();
    }
}
