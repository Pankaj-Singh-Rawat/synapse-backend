package com.synapse.auth.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // 1️⃣ No Authorization header → continue (Spring will block later)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Extract JWT
        String jwtToken = authHeader.substring(7);

        try {
            // 3️⃣ Validate + extract data
            String username = jwtUtil.extractUsername(jwtToken);
            String role = jwtUtil.extractRole(jwtToken);

            // 4️⃣ Convert role to Spring authority
            var authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + role)
            );

            // 5️⃣ Create Authentication object
            var authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );

            // 6️⃣ Store authentication in SecurityContext
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

        } catch (Exception e) {
            // Invalid / expired / tampered token
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 7️⃣ Continue request
        filterChain.doFilter(request, response);
    }
}
