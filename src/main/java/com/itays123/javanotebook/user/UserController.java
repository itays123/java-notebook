package com.itays123.javanotebook.user;

import com.itays123.javanotebook.JavaNotebookApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles user-related requests
 */
@RestController
@RequestMapping(path = "api/auth")
public class UserController {

    static final Logger log =
            LoggerFactory.getLogger(JavaNotebookApplication.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns user data
     * @param request the request data
     * @return the requester profile
     */
    @GetMapping
    public UserProfile getProfile(HttpServletRequest request) {
        log.info("Handling profile request with {}", request.getUserPrincipal().getName());
        return userService.getProfile(request.getUserPrincipal().getName());
    }

    /**
     * Commits a login action
     * @param user the auth credentials
     * @param response the response object
     * @throws EmailNotFoundException if email is not in use
     * @throws PasswordIncorrectException if password doesn't match
     * @return the authentication token if login successful
     */
    @PostMapping(path = "/login")
    public String login(@RequestBody User user, HttpServletResponse response) {
        String token = userService.login(user.getEmail(), user.getPassword());
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");
        tokenCookie.setDomain("localhost");
        response.addCookie(tokenCookie);
        return token;
    }

    /**
     * Commits a register action
     * @param user the user to register
     * @param response the response object
     * @throws EmailInUseException if email is already in use
     * @return the authentication token
     */
    @PostMapping(path = "/register")
    public String register(@RequestBody User user, HttpServletResponse response) {
        String token = userService.register(user.getName(), user.getEmail(), user.getPassword());
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");
        tokenCookie.setDomain("localhost");
        response.addCookie(tokenCookie);
        return token;
    }

    /**
     * Deletes the auth cookie from the session
     * @param response the response object.
     */
    @DeleteMapping
    public void logout(HttpServletResponse response) {
        Cookie tokenCookie = new Cookie("token", null);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");
        tokenCookie.setDomain("localhost");
        tokenCookie.setMaxAge(0);
        response.addCookie(tokenCookie);
    }
}
