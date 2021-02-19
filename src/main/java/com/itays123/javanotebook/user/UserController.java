package com.itays123.javanotebook.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @PostMapping(path = "/register")
    public String register(@RequestBody User user) {
        return userService.register(user.getName(), user.getEmail(), user.getPassword());
    }
}
