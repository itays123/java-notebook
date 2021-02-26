import { createContext, useContext, useState } from "react";

const NoteEditorContext = createContext();

export function useNoteEditorContext() {
    return useContext(NoteEditorContext);
}

const NoteEditorContextProvider = ( {children, initialTitle} ) => {
    const [focusedBlockIndex, setFocusedBlockIndex] = useState(-1);
    const [title, setTitle] = useState(initialTitle);
    return ( 
        <NoteEditorContext.Provider value={{
            focusedBlockIndex,
            setFocusedBlockIndex,
            title,
            setTitle,
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