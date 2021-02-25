import { useEffect, useState } from 'react';

export function useBlockEditor(blocks = [], onChange = () => {}) {
  const [added, setAdded] = useState([]);
  const [modifiedMap, setModifiedMap] = useState(new Map());
  const [deleted, setDeleted] = useState([]);
  const changes = () => {
    let modified = [];
      for (let modifiedBlock of modifiedMap.values()) modified.push(modifiedBlock);
      return { addedBlocks: added, updatedBlocks: modified, deletedBlocks: deleted }
  }

  useEffect(() => {
    console.log(added, deleted, modifiedMap);
    onChange(added, deleted, modifiedMap);
  }, [added, deleted, modifiedMap, onChange])
  return {
    added,
    deleted,
    changes,
    addBlock(type = 'P', content = '') {
      setAdded(array => [...array, { type, content }]);
    },
    modifyBlockContent(index, content) {
      // if existed
      if (index < blocks.length) {
          setModifiedMap(map => {
              let block = blocks[index];
              return new Map(map.set(block.id, { ...block, content }));
          })
      }
    },
    modifyBlockType(index, type) {
        // if existed
        let modifiedBlockId = blocks[index].id;
        if (modifiedBlockId) {
            setModifiedMap(map => {
                let block = blocks[index];
                return new Map(map.set(modifiedBlockId, { ...block ,type }))
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