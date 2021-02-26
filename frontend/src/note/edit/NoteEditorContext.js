import { createContext, useContext, useState } from "react";
import { useBlockEditor } from "../../block/edit/useBlockEditor";

const NoteEditorContext = createContext();

export function useNoteEditorContext() {
    return useContext(NoteEditorContext);
}

const NoteEditorContextProvider = ( {children, initialTitle = '', initialBlocks = [], saveNote = () => {}, deleteNote = () => {}} ) => {
    const [focusedBlockIndex, setFocusedBlockIndex] = useState(-1);
    const [title, setTitle] = useState(initialTitle);
    const blockEditor = useBlockEditor(initialBlocks);

    return ( 
        <NoteEditorContext.Provider value={{
            focusedBlockIndex,
            setFocusedBlockIndex,
            next() {
                if (focusedBlockIndex + 1 === blockEditor.blocks.size) {
                    blockEditor.addBlock();
                }
                setFocusedBlockIndex(i=>++i);
            },
            prev() {
                setFocusedBlockIndex(i=>--i);
            },
            title,
            setTitle,
            ...blockEditor,
            isChanged() {
                return title !== initialTitle
                    || blockEditor.added.size > 0
                    || blockEditor.modified.size > 0
                    || blockEditor.deleted.size > 0
            },
            saveNote() {
                saveNote({ 
                    title,
                    addedBlocks: [...blockEditor.added.values()],
                    updatedBlocks: [...blockEditor.modified.values()],
                    deletedBlocks: [...blockEditor.deleted.values()],
                 })
            },
            deleteNote
        }}>
            {children}
        </NoteEditorContext.Provider>
     );
}
 
export default NoteEditorContextProvider;