package com.itays123.javanotebook.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "api/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getProfile() {
        return userService.getProfile("");
    }

    @PostMapping(path = "/login")
    public String login(@RequestBody User user, HttpServletResponse response) {
        String token = userService.login(user.getEmail(), user.getPassword());
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setHttpOnly(true);
        response.addCookie(tokenCookie);
        return token;
    }

    @PostMapping(path = "/register")
    public String register(@RequestBody User user, HttpServletResponse response) {
        String token = userService.register(user.getName(), user.getEmail(), user.getPassword());
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setHttpOnly(true);
        response.addCookie(tokenCookie);
        return token;
    }
}
