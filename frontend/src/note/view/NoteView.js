import { useParams } from "react-router-dom";
import BlockList from "../../block/BlockList";
import NoteEditorContextProvider from "../edit/NoteEditorContext";
import { useDeleteNote } from "../edit/useDeleteNote";
import { useSaveNote } from "../edit/useSaveNote";
import { useNote } from "../useNote";
import NoteTitle from "./NoteTitle";

const NoteView = () => {
    const { id } = useParams();
    const note = useNote(id);
    const saveNote = useSaveNote(id);
    const deleteNote = useDeleteNote(id);

    return ( 
      <NoteEditorContextProvider 
        initialTitle={note.title} 
        initialBlocks={note.content} 
        saveNote={saveNote}
        deleteNote={deleteNote}
        >
          <div className="note scrollable">
              <div className="container mx-auto">
                <header className="pt-8 pb-2">
                  <NoteTitle />
                  <h2 className="text-xl font-bold text-gray-600 mt-2">By {note.user.name}</h2>
                </header>
                <main>
                  <BlockList />
                </main>
              </div>
          </div>
        </NoteEditorContextProvider>
     );
}
 
export default NoteView;