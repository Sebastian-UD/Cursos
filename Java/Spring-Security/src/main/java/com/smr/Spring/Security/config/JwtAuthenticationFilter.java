package com.smr.Spring.Security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Extraer el encabezado Authorization
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Validar que el token JWT existe en la petición HTTP
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return; // No hay token en la solicitud, continuar con la siguiente cadena de filtros
        }
        //--- Comprobar que el usuario guardado en el token JWT exista en la BD ---//
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUserName(jwt);

        // Comprobar que el usuario esté en el token JWT, y que no está previamente autenticado
        // en la aplicación
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){

            // Cargar los detalles del usuario desde la base de datos
            UserDetails userDetails = userDetailService.loadUserByUsername(userEmail);

            // Verificar si el token es válido
            if(jwtService.isTokenValid(jwt, userDetails)){

                // Crear una autenticación y almacenarla en el contexto de seguridad
                // UsernamePasswordAuthenticationToken representa una autenticación exitosa en Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, // Principal: Información del usuario (userDetails)
                        null, // Credentials: En este caso null, porque no se usa contraseñas en cada solicitud (solo el token JWT).
                        userDetails.getAuthorities() // Authorities: Lista de roles o permisos del usuario
                );
                // Antes de almacenar la autenticación, se agregan detalles de la solicitud actual, como dirección IP, sesión, etc.
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
