package com.itays123.javanotebook.auth;

import com.itays123.javanotebook.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    static final Logger log =
            LoggerFactory.getLogger(JWTFilter.class);

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
        log.info("starting filter");
        try {
            UsernamePasswordAuthenticationToken authenticationToken = getValidToken(request);
            log.info("successfully authenticated user");
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (AuthenticationException exception) {
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getValidToken(HttpServletRequest request) throws AuthenticationException {
        log.info("Authentication token initialization started");
        if(request.getCookies() == null) {
            throw new AuthenticationException("No cookies") {
                @Override
                public String getMessage() {
                    return "No auth cookie provided";
                }
            };
        }
        log.info("Found cookies in request object");
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
        log.info("Found cookie named token");
        String token = tokenCookie.getValue();
        String email = jwtUtils.getUsernameFromToken(token);
        log.info("email decoded, retrieved {}", email);
        UserDetails user = userService.loadUserByUsername(email);

        log.info(user.toString());

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
