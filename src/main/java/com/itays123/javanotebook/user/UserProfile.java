package com.itays123.javanotebook.user;

import com.itays123.javanotebook.note.NoteTitleAndId;

import java.util.List;

public class UserProfile {

    private Long id;

    private String name;

    private String email;

    private List<NoteTitleAndId> notes;

    public UserProfile() {
    }

    public UserProfile(Long id, String name, String email, List<NoteTitleAndId> notes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.notes = notes;
    }

    public static UserProfile fromUserAndNotes(User user, List<NoteTitleAndId> notes) {
        return new UserProfile(user.getId(), user.getName(), user.getEmail(), notes);
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

    public List<NoteTitleAndId> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteTitleAndId> notes) {
        this.notes = notes;
    }
}
