import { useEffect, useState } from 'react';

function asMap(blocks = []) {
  const map = new Map();
  blocks.forEach((block, index) => {
    map.set(index, block);
  })
  return map;
}

export function useBlockEditor(initialBlocks = []) {
  const [blocks, setBlocks] = useState(asMap(initialBlocks));
  const [modified, setModified] = useState(new Map());
  const [added, setAdded] = useState(new Map());
  const [deleted, setDeleted] = useState(new Set());

  const updateMap = (k,v) => {
    setBlocks(map => new Map(map.set(k,v)));
  }

  return {
    blocks,
    saveBlock(index, updatedContent, updatedType) {
      const block = blocks.get(index);
      if (block) updateMap(index, { ...block, content: updatedContent, type: updatedType });
    },
    addBlock(type = 'P', content = '') {
      const newBlock = { type, content };
      const index = blocks.size;
      updateMap(index, newBlock);
      setAdded(map => {
          return new Map(map.set(index, { type, content }));
      })
    },
    modifyBlockContent(index, content) {
      let modifiedBlock = blocks.get(index);
      if (modifiedBlock.id) {
          setModified(map => {
              return new Map(map.set(modifiedBlock.id, { ...modifiedBlock, content }));
          })
      } else if (added.has(index)) {
        setAdded(map => {
            return new Map(map.set(index, { ...modifiedBlock, content }));
        })
      }
    },
    deleteBlock(index) {
      // if existed, initial index
      const block = blocks.get(index);
      if (block.id) {
        setModified(m => {
          let map = new Map(m);
          map.delete(block.id);
          return map;
        })
        setDeleted(s => new Set(s.add(block.id)))
      } else if (block) {
        setAdded(m => {
          let map = new Map(m);
          map.delete(index);
          return map;
        })
      }
      setBlocks(m => {
        let map = new Map(m);
        map.delete(index);
        return map;
      })
    },
  };
}