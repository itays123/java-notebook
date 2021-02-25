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

  const updateMap = (k,v) => {
    setBlocks(map => new Map(map.set(k,v)));
  }

  useEffect(() => { console.log(blocks) }, [blocks]);

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
    },
    modifyBlockContent(index, content) {
      // if existed
      /*
      let modifiedBlock = blocks.get(index);
      if (modifiedBlock.id) {
          setModifiedMap(map => {
              return new Map(map.set(modifiedBlock.id, { ...modifiedBlock, content }));
          })
      }
      */
    },
    modifyBlockType(index, type) {
        // if existed
        /*
        let modifiedBlockId = blocks[index].id;
        if (modifiedBlockId) {
            setModifiedMap(map => {
                let block = blocks[index];
                return new Map(map.set(modifiedBlockId, { ...block ,type }))
            })
        }
        */
      },
    deleteBlock(index) {
      // if existed, initial index
      setBlocks(m => {
        let map = new Map(m);
        map.delete(index);
        return map;
      })
    },
  };
}