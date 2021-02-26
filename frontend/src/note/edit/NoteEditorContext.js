import { createContext, useContext, useState } from "react";
import { useHistory } from "react-router-dom";
import { useBlockEditor } from "../../block/edit/useBlockEditor";

const NoteEditorContext = createContext();

export function useNoteEditorContext() {
    return useContext(NoteEditorContext);
}

const NoteEditorContextProvider = ( {children, initialTitle, initialBlocks} ) => {
    const [focusedBlockIndex, setFocusedBlockIndex] = useState(-1);
    const { push } = useHistory();
    const [title, setTitle] = useState(initialTitle);
    const blockEditor = useBlockEditor(initialBlocks);

    return ( 
        <NoteEditorContext.Provider value={{
            focusedBlockIndex,
            setFocusedBlockIndex,
            next() {
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
            deleteNote() {
                push('/')
            },
            saveNote() {}
        }}>
            {children}
        </NoteEditorContext.Provider>
     );
}
 
export default NoteEditorContextProvider;