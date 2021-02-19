package com.itays123.javanotebook.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new EmailNotFoundException("Could not find user with email " + email);
                });
        return user.getName();
    }

    public String register(String name, String email, String password) {
        if(userRepository.findByEmail(email).isPresent()) throw new EmailInUseException("Email " + email + " is already in use");
        return name;
    }

    public User getProfile(String token) {
        return userRepository.findById(1L).orElseThrow();
    }

}
