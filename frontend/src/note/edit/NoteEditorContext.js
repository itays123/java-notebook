import { createContext, useContext, useState } from "react";
import { useBlockEditor } from "../../block/edit/useBlockEditor";

const NoteEditorContext = createContext();

export function useNoteEditorContext() {
    return useContext(NoteEditorContext);
}

const NoteEditorContextProvider = ( {children, initialTitle, initialBlocks} ) => {
    const [focusedBlockIndex, setFocusedBlockIndex] = useState(-1);
    const [title, setTitle] = useState(initialTitle);
    const blockEditor = useBlockEditor(initialBlocks);

    return ( 
        <NoteEditorContext.Provider value={{
            focusedBlockIndex,
            setFocusedBlockIndex,
            title,
            setTitle,
            ...blockEditor,
            next() {
                setFocusedBlockIndex(i=>++i);
            },
            prev() {
                setFocusedBlockIndex(i=>--i);
            }
        }}>
            {children}
        </NoteEditorContext.Provider>
     );
}
 
export default NoteEditorContextProvider;