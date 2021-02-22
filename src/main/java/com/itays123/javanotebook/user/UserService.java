package com.itays123.javanotebook.user;

import com.itays123.javanotebook.auth.JWTUtils;
import com.itays123.javanotebook.note.NoteRepository;
import com.itays123.javanotebook.note.NoteTitleAndId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final NoteRepository noteRepository;

    private final JWTUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository, NoteRepository noteRepository, JWTUtils jwtUtils) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
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

    public UserProfile getProfile(String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    List<NoteTitleAndId> notes = noteRepository.findUserNotes(user.getId());
                    return UserProfile.fromUserAndNotes(user, notes);
                }).orElseThrow(() -> {throw new EmailNotFoundException("Could not find user with email " + email);});
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s).orElseThrow(() -> {throw new UsernameNotFoundException("");});
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of((GrantedAuthority) () -> "user"));
    }
}
