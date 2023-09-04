package com.backend.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.service.JWTAuthNService;
import com.backend.service.MyWSUserDetailsService;

@Component
public class JWTAuthRFilter extends OncePerRequestFilter {

    @Autowired
    private JWTAuthNService jwtAuthNService;

    @Autowired
    private MyWSUserDetailsService myWSUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authRHeader = request.getHeader("Authorization");
        String bearerPrefix = "Bearer ";
        String token = null;
        String username = null;

        if (authRHeader != null && authRHeader.startsWith(bearerPrefix)) {
            token = authRHeader.substring(bearerPrefix.length());
            username = jwtAuthNService.getUsernameFromToken(token);
        }

        Authentication authN = SecurityContextHolder.getContext().getAuthentication();
        if (username != null && authN == null) {
            UserDetails userDetails = myWSUserDetailsService.loadUserByUsername(username);
            if (jwtAuthNService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authNToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authNToken);
            }
        }

        filterChain.doFilter(request, response);

    }

}
