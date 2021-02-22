package com.itays123.javanotebook.auth;

import com.itays123.javanotebook.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JWTFilter extends BasicAuthenticationFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserService userService;

    public JWTFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = getValidToken(request);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (AuthenticationException exception) {
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getValidToken(HttpServletRequest request) throws AuthenticationException {
        if(request.getCookies() == null) {
            throw new AuthenticationException("No cookies") {
                @Override
                public String getMessage() {
                    return "No auth cookie provided";
                }
            };
        }
        Cookie tokenCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("token"))
                .findFirst()
                .orElseThrow(() -> {
                    throw new AuthenticationException("No auth cookie provided") {
                        @Override
                        public String getMessage() {
                            return "No auth cookie provided";
                        }
                    };
                });
        String token = tokenCookie.getValue();
        String email = jwtUtils.getUsernameFromToken(token);
        UserDetails user = userService.loadUserByUsername(email);

        logger.debug(user);

        if (jwtUtils.validateToken(token, user)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );
            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            return usernamePasswordAuthenticationToken;
        } else {
            throw new AuthenticationException("Token not validated or expired") {
                @Override
                public String getMessage() {
                    return "Token not validated or expired";
                }
            };
        }
    }
}
