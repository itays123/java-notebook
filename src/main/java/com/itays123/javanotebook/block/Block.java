package com.itays123.javanotebook.block;

import javax.persistence.*;

/**
 * The Block Entity
 */
@Entity
@Table(name="blocks")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.ORDINAL)
    private BlockType type;

    public Block() {
    }

    public Block(Long id, String content, BlockType type) {
        this.id = id;
        this.content = content;
        this.type = type;
    }

    public Block(String content, BlockType type) {
        this.content = content;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
