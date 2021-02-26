import { useNoteEditorContext } from "../edit/NoteEditorContext";
import NoteTitleEdit from "../edit/NoteTitleEdit";

const NoteTitle = () => {
    const { focusedBlockIndex, setFocusedBlockIndex, title } = useNoteEditorContext();
    return focusedBlockIndex === -1 
        ? <NoteTitleEdit /> 
        : <h1 
            className="pt-2 pb-1 text-5xl font-bold text-gray-800 leading-snug" 
            onClick={() => setFocusedBlockIndex(-1)}>
            {title}
          </h1>;
}
 
export default NoteTitle;