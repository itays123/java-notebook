import { useEffect, useRef } from "react";
import { useNoteEditorContext } from "./NoteEditorContext";

const NoteTitleEdit = () => {
    const {title, setTitle, next} = useNoteEditorContext();
    const ref = useRef();

    useEffect(() => {
        ref.current.focus();
    }, []);

    return ( 
        <input
            type="text"
            value={title}
            ref={ref}
            onChange={e => setTitle(e.target.value)}
            className="pt-2 pb-1 my-0 text-5xl font-bold text-gray-800 focus:outline-none leading-snug"
            placeholder="Untitled Note"
            onKeyPress={e => e.key === 'Enter' && next()}
        />
     );
}
 
export default NoteTitleEdit;