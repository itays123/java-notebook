package com.itays123.javanotebook.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s).orElseThrow(() -> {throw new UsernameNotFoundException("");});
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
