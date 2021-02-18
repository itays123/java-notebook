package com.itays123.javanotebook.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itays123.javanotebook.block.Block;
import org.springframework.lang.NonNull;

import java.util.List;

public class UpdateableNote {

    private String title;

    private List<Block> addedBlocks;
    private List<Block> updatedBlocks;
    private List<Long> deletedBlock;

    public UpdateableNote() {
    }

    public UpdateableNote(
            String title,
            @JsonProperty(defaultValue = "[]") List<Block> addedBlocks,
            @JsonProperty(defaultValue = "[]") List<Block> updatedBlocks,
            @JsonProperty(defaultValue = "[]") List<Long> deletedBlock
    ) {
        this.title = title;
        this.addedBlocks = addedBlocks;
        this.updatedBlocks = updatedBlocks;
        this.deletedBlock = deletedBlock;
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

    public List<Long> getDeletedBlock() {
        return deletedBlock;
    }

    public void setDeletedBlock(List<Long> deletedBlock) {
        this.deletedBlock = deletedBlock;
    }
}
