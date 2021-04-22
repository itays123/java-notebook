package com.itays123.javanotebook.note;

import com.itays123.javanotebook.block.Block;
import com.itays123.javanotebook.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Note entity
 */
@Entity
@Table(name="notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String title;

    @OneToMany(targetEntity = Block.class, cascade = CascadeType.ALL)
    @JoinColumn(name="note_id", referencedColumnName = "id")
    private List<Block> content = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", updatable = false)
    private User user;

    public Note() {
    }

    public Note(String title) {
        this.title = title;
    }

    public Note(Long id, String title) {
        this.id = id;
        this.title = title;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
