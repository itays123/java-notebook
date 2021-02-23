package com.itays123.javanotebook.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itays123.javanotebook.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateableNote {

    private String title;

    private List<Block> addedBlocks;
    private List<Block> updatedBlocks;
    private List<Long> deletedBlocks;

    public UpdateableNote() {
    }

    public UpdateableNote(
            String title,
            List<Block> addedBlocks,
            List<Block> updatedBlocks,
            List<Long> deletedBlocks
    ) {
        this.title = title;
        this.addedBlocks = addedBlocks;
        this.updatedBlocks = updatedBlocks;
        this.deletedBlocks = deletedBlocks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Block> getAddedBlocks() {
        return addedBlocks;
    }

    public void setAddedBlocks(List<Block> addedBlocks) {
        this.addedBlocks = addedBlocks;
    }

    public List<Block> getUpdatedBlocks() {
        return updatedBlocks;
    }

    public void setUpdatedBlocks(List<Block> updatedBlocks) {
        this.updatedBlocks = updatedBlocks;
    }

    public List<Long> getDeletedBlocks() {
        return deletedBlocks;
    }

    public void setDeletedBlocks(List<Long> deletedBlocks) {
        this.deletedBlocks = deletedBlocks;
    }

    public void ensureValid() {
        if (title == null) title = "";
        if (addedBlocks == null) addedBlocks = new ArrayList<>();
        if (updatedBlocks == null) updatedBlocks = new ArrayList<>();
        if (deletedBlocks == null) deletedBlocks = new ArrayList<>();
    }

    public static Note applyChanges(Note note, UpdateableNote updateableNote) {
        updateableNote.ensureValid();
        if (!updateableNote.getTitle().isBlank()) note.setTitle(updateableNote.getTitle());
        List<Block> content = note.getContent()
                .stream()
                .filter(block -> updateableNote.getDeletedBlocks().stream().noneMatch(deletedBlockId -> deletedBlockId.equals(block.getId())))
                .collect(Collectors.toList());
        content.forEach(block -> {
            updateableNote.getUpdatedBlocks()
                .stream()
                .filter(updatedBlock -> updatedBlock.getId().equals(block.getId()))
                .forEach(updatedBlock -> {
                    block.setType(updatedBlock.getType());
                    block.setContent(updatedBlock.getContent());
            });
        });
        content.addAll(updateableNote.getAddedBlocks());
        note.setContent(content);
        return note;
    }
}
