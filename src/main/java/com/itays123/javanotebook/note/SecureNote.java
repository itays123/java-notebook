package com.itays123.javanotebook.note;

import com.itays123.javanotebook.block.Block;
import com.itays123.javanotebook.user.SecureUser;

import java.util.List;

// The note returned when asking for an individual note, returning secured user;
public class SecureNote {

    private Long id;

    private String title;

    private List<Block> content;

    private SecureUser user;

    public static SecureNote fromNote(Note note) {
        return new SecureNote(note.getId(), note.getTitle(), note.getContent(), SecureUser.fromUser(note.getUser()));
    }

    public SecureNote() {
    }

    public SecureNote(Long id, String title, List<Block> content, SecureUser user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Block> getContent() {
        return content;
    }

    public void setContent(List<Block> content) {
        this.content = content;
    }

    public SecureUser getUser() {
        return user;
    }

    public void setUser(SecureUser user) {
        this.user = user;
    }
}
