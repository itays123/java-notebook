package com.itays123.javanotebook.block;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {
    public List<Block> getBlocks() {
        return List.of(
                new Block(1L, "hello world", BlockType.H1)
        );
    }
}
