import { createContext, useContext, useState } from "react";

const NoteEditorContext = createContext();

export function useNoteEditorContext() {
    return useContext(NoteEditorContext);
}

const NoteEditorContextProvider = ( {children} ) => {
    const [focusedBlockIndex, setFocusedBlockIndex] = useState(-1);
    return ( 
        <NoteEditorContext.Provider value={{
            focusedBlockIndex,
            setFocusedBlockIndex,
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