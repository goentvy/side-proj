package com.entvy.openbidhub.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private static final List<String> WHITELIST = List.of(
            "/api/login",
            "/api/signup",
            "/api/onbid",
            "/api/raw-items",
            "/api/raw-items/import",
            "/api/auction-items/import",
            "/api/items",
            "/swagger-ui",
            "/v3/api-docs",
            "/api-docs",
            "/swagger-ui.html"
    );

    private boolean isWhitelisted(String path) {
        return WHITELIST.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if ("OPTIONS".equalsIgnoreCase(request.getMethod()) || isWhitelisted(path)) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = jwtUtil.validateToken(token);
                String email = claims.getSubject();
                String role = claims.get("role", String.class);

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(email, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    request.setAttribute("claims", claims);
                }

            } catch (ExpiredJwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
                return;
            } catch (JwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
            return;
        }

        chain.doFilter(request, response);
    }
}