package com.leoni.execution.contrats.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 🔓 Désactive CSRF pour Postman (attention en prod)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // ✅ Autorise toutes les requêtes API
                        .anyRequest().permitAll() // ✅ Autorise aussi tout le reste (évite 401 sur favicon, etc.)
                )
                .httpBasic(Customizer.withDefaults()) // 👈 Ajoute l’auth basic si jamais nécessaire
                .formLogin(form -> form.disable()); // ❌ Pas de login HTML

        return http.build();
    }
}
