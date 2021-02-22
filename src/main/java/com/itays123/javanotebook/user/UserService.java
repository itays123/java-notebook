package com.itays123.javanotebook.user;

import com.itays123.javanotebook.auth.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final JWTUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository, JWTUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new EmailNotFoundException("Could not find user with email " + email);
                });
        return jwtUtils.generateToken(user);
    }

    public String register(String name, String email, String password) {
        if(userRepository.findByEmail(email).isPresent()) throw new EmailInUseException("Email " + email + " is already in use");
        User user = userRepository.save(new User(name, email, password));
        return jwtUtils.generateToken(user);
    }

    public User getProfile(String token) {
        return userRepository.findById(1L).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s).orElseThrow(() -> {throw new UsernameNotFoundException("");});
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of((GrantedAuthority) () -> "user"));
    }
}
