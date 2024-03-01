package com.compassuol.sp.challenge.msuser.domain.jwt;

import com.compassuol.sp.challenge.msuser.domain.jwt.service.UserDetailsService;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.JwtService;
import com.compassuol.sp.challenge.msuser.web.exception.ApiExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{


    private final JwtService service;

    private final UserDetailsService userDetailsService;

    private final ApiExceptionHandler handler;

    public static final String JWT_BEARER = "Bearer ";

    public static final String JWT_AUTHORIZATION = "Authorization";


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            final String authHeader = request.getHeader(JWT_AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith(JWT_BEARER)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authHeader.substring(JWT_BEARER.length());
            String email = service.extractEmail(token);


            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                if (service.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                                    new WebAuthenticationDetailsSource().buildDetails(request)
                            );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (
        MalformedJwtException ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            (response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");

        }
        catch (
        ExpiredJwtException ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            (response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Expired JWT token");

        }
        catch (
        UnsupportedJwtException ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            (response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unsupported JWT token");

        }
        catch(IllegalArgumentException ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            (response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT claims string is empty");

        }
    }

}
