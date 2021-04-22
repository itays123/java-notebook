package com.itays123.javanotebook.user;

import com.itays123.javanotebook.note.Note;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The user entity
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String email;

    private String password;

    @OneToMany(targetEntity = Note.class, cascade = CascadeType.ALL)
    private Set<Note> notes = new HashSet<>();

    public User() {
    }

    public User(Long id, String name, String email, String password, Set<Note> notes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.notes = notes;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;

        for(Note note : notes) {
            note.setUser(this);
        }
    }
}
