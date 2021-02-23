package com.itays123.javanotebook.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itays123.javanotebook.block.Block;

import java.util.List;

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
}
