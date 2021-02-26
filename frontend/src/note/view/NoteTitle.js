import { useNoteEditorContext } from "../edit/NoteEditorContext";
import NoteTitleEdit from "../edit/NoteTitleEdit";

const NoteTitle = () => {
    const { focusedBlockIndex, setFocusedBlockIndex, title, deleteNote, saveNote, isChanged } = useNoteEditorContext();
    return (
        <div className="note-title flex items-center">
            {focusedBlockIndex === -1 
                ? <NoteTitleEdit /> 
                : <h1 
                    className="pt-2 pb-1 text-5xl font-bold text-gray-800 leading-snug flex-grow" 
                    onClick={() => setFocusedBlockIndex(-1)}>
                    {title}
                </h1>
          }
          <button onClick={() => saveNote()} disabled={!isChanged()} className="disabled:opacity-50 mx-2">
              <svg xmlns="http://www.w3.org/2000/svg" 
              viewBox="0 0 24 24" 
              className="fill-current text-gray-800" 
              width="24px" 
              height="24px">
                  <path d="M0 0h24v24H0z" fill="none"/>
                  <path d="M9 16.2L4.8 12l-1.4 1.4L9 19 21 7l-1.4-1.4L9 16.2z"/>
              </svg>
          </button>
          <button onClick={() => deleteNote()}>
              <svg xmlns="http://www.w3.org/2000/svg" 
              viewBox="0 0 24 24" 
              className="fill-current text-gray-800" 
              width="24px" 
              height="24px">
                  <path d="M0 0h24v24H0z" fill="none"/>
                  <path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"/>
              </svg>
          </button>
        </div>
    );
}
 
export default NoteTitle;