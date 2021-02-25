import { useState } from 'react';

export function useBlockEditor(blocks = []) {
  const [added, setAdded] = useState([]);
  const [modifiedMap, setModifiedMap] = useState(new Map());
  const [deleted, setDeleted] = useState([]);
  return {
    added,
    deleted,
    changes() {
        let modified = [];
        for (let modifiedBlock of modifiedMap.values()) modified.push(modifiedBlock);
        return { addedBlocks: added, updatedBlocks: modified, deletedBlocks: deleted }
    },
    addBlock(value) {
      setAdded(array => [...array, value]);
    },
    modifyBlockContent(index, content) {
      // if existed
      let modifiedBlockId = blocks[index].id;
      if (modifiedBlockId) {
          setModifiedMap(map => {
              let block = blocks[index];
              map.set(modifiedBlockId, { ...block, content })
              return map;
          })
      }
    },
    modifyBlockType(index, type) {
        // if existed
        let modifiedBlockId = blocks[index].id;
        if (modifiedBlockId) {
            setModifiedMap(map => {
                let block = blocks[index];
                return new Map(map.set(modifiedBlockId, block))
            })
        }
      },
    deleteBlock(index) {
      // if existed, initial index
      let deletedBlockId = blocks[index].id;
      if (deletedBlockId) setDeleted(array => [...array, deletedBlockId]);
    },
  };
}