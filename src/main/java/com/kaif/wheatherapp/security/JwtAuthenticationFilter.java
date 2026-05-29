package com.kaif.wheatherapp.security;

import com.kaif.wheatherapp.entity.User;
import com.kaif.wheatherapp.repo.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final AuthUtil authUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("incoming request: {}",request.getRequestURI());

        final String requestTokenHeader =
                request.getHeader("Authorization");

        if(requestTokenHeader != null &&
                requestTokenHeader.startsWith("Bearer ")){

            String token = requestTokenHeader.substring(7);

            String username =
                    authUtil.getUsernameFromToken(token);

            if(username != null &&
                    SecurityContextHolder.getContext()
                            .getAuthentication() == null){

                User user = userRepository
                        .findByUsername(username)
                        .orElse(null);

                if(user != null){

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    user.getAuthorities()
                            );

                    SecurityContextHolder.getContext()
                            .setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
