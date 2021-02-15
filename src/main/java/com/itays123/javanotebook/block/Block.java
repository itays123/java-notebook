package com.itays123.javanotebook.block;

public class Block {
    private Long id;
    private String content;
    private BlockType type;
    private Long noteId;

    public Block() {
    }

    public Block(Long id, String content, BlockType type, Long noteId) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.noteId = noteId;
    }

    public Block(String content, BlockType type, Long noteId) {
        this.content = content;
        this.type = type;
        this.noteId = noteId;
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

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", noteId=" + noteId +
                '}';
    }
}
