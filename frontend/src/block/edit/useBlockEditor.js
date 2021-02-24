import { useState } from 'react';

export function useBlockEditor(initialDeck = []) {
  const [added, setAdded] = useState([]);
  const [modifiedMap, setModifiedMap] = useState(new Map());
  const [deleted, setDeleted] = useState([]);
  const [deck, setDeck] = useState(initialDeck);
  return {
    deck,
    changes() {
        let modified = [];
        for (let modifiedBlock of modifiedMap.values()) modified.pushn(modifiedBlock);
        return { addedBlocks: added, updatedBlocks: modified, deletedBlocks: deleted }
    },
    addBlock(value) {
      setAdded(array => [...array, value]);
      setDeck(array => [...array, value]);
    },
    modifyBlockContent(index, content) {
      // if existed
      let modifiedBlockId = deck[index].id;
      if (modifiedBlockId) {
          setModifiedMap(map => {
              let block = deck[index];
              map.set(modifiedBlockId, { ...block, content })
          })
      }
      setDeck(array => {
        let newArray = array;
        newArray[index].content = content;
        return newArray;
      });
    },
    modifyBlockType(index, type) {
        // if existed
        let modifiedBlockId = deck[index].id;
        if (modifiedBlockId) {
            setModifiedMap(map => {
                let block = deck[index];
                map.set(modifiedBlockId, { ...block, type })
            })
        }
        setDeck(array => {
          let newArray = array;
          newArray[index].type = type;
          return newArray;
        });
      },
    deleteBlock(index) {
      // if existed, initial index
      let deletedBlockId = deck[index].id;
      if (deletedBlockId) setDeleted(array => [...array, deletedBlockId]);
      setDeck(deck => deck.splice(index, 1));
    },
  };
}