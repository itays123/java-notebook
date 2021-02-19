package com.itays123.javanotebook.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
}
