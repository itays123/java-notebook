package com.itays123.javanotebook.user;

import com.itays123.javanotebook.auth.JWTUtils;
import com.itays123.javanotebook.note.NoteRepository;
import com.itays123.javanotebook.note.NoteTitleAndId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The User service
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final NoteRepository noteRepository;

    private final JWTUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, NoteRepository noteRepository, JWTUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Performs an email-password operation
     * @param email the email of the user
     * @param password the password of the user
     * @throws EmailNotFoundException if email is not in use
     * @throws PasswordIncorrectException if password is incorrect
     * @return the authentication token
     */
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new EmailNotFoundException("Could not find user with email " + email);
                });
        if(passwordEncoder.matches(password, user.getPassword()))
            return jwtUtils.generateToken(user);
        else throw new PasswordIncorrectException("Passwords Don't match");
    }

    /**
     * Creates a new user
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @return the authentication token
     */
    public String register(String name, String email, String password) {
        if(userRepository.findByEmail(email).isPresent()) throw new EmailInUseException("Email " + email + " is already in use");
        String hashedPassword = passwordEncoder.encode(password);
        User user = userRepository.save(new User(name, email, hashedPassword));
        return jwtUtils.generateToken(user);
    }

    /**
     * Queries the user profile
     * @param email the email of the user
     * @return a UserProfile object.
     */
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
