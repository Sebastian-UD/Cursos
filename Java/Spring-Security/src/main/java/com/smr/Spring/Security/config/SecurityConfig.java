package com.smr.Spring.Security.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Habilita Spring Security en la aplicación
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jstAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(crsf -> crsf.disable()) // Deshabilita CSRF (recomendado para APIs REST)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No usa sesiones
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/api/v1/auth/**").permitAll() // Permite acceso libre a rutas de autenticación
                        .requestMatchers(HttpMethod.GET, "/public/**").permitAll() // Permite solo lectura en rutas públicas
                        .anyRequest().authenticated() // Protege todas las demás rutas
                )
                .authenticationProvider(authenticationProvider) // Configura el proveedor de autenticación
                .addFilterBefore(jstAuthFilter, UsernamePasswordAuthenticationFilter.class); // Agrega el filtro de JWT antes del filtro de autenticación de Spring

        return http.build();
    }
}
