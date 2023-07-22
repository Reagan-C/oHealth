package com.myhealth.oHealth.config.filter;

import com.myhealth.oHealth.model.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private  UserDetailsService userDetailsService;

    @Autowired
    private  JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !StringUtils.startsWith(header, "Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        String username = null;
        try {
            username = jwtUtil.getUsernameFromToken(token);
        }catch (JwtException e) {
            logger.error("Cannot get username from token");
        }

        if (StringUtils.isEmpty(username)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, userDetails)) {
                try {
                    UsernamePasswordAuthenticationToken userAuthToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null,
                                    userDetails.getAuthorities());
                    userAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(userAuthToken);
                } catch (Exception ex) {
                    logger.error("Cannot set user authentication");
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
