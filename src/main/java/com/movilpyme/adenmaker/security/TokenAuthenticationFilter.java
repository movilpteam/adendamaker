package com.movilpyme.adenmaker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    /*@Value("$(jwt.header)")
    private String AUTH_HEADER;*/

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private UserDetailServiceImpl userDetailsServiceImpl;

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
        if ( authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String error = "";
        String authToken = getToken(httpServletRequest);
        if (authToken != null) {

            // Get username from token
            String username = tokenHelper.getUsernameFromToken( authToken );
            if ( username != null ) {

                // Get user
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername( username );

                // Create authentication
                TokenBasedAuthentication authentication = new TokenBasedAuthentication( userDetails );
                authentication.setToken( authToken );
                SecurityContextHolder.getContext().setAuthentication( authentication );
            } else {
                error = "Username from token can't be found in DB.";
            }
        } else {
            error = "Authentication failed - no Bearer token provided.";
        }
        if( ! error.equals("")){
            // System.out.println(error);
            SecurityContextHolder.getContext().setAuthentication( new AnonAuthentication() );//prevent show login form...
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
