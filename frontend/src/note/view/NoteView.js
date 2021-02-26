import { useParams } from "react-router-dom";
import BlockList from "../../block/BlockList";
import NoteEditorContextProvider from "../edit/NoteEditorContext";
import { useNote } from "../useNote";
import NoteTitle from "./NoteTitle";

const NoteView = () => {
    const { id } = useParams();
    const note = useNote(id);

    return ( 
      <NoteEditorContextProvider initialTitle={note.title}>
        <div className="note scrollable">
            <div className="container mx-auto">
              <header className="pt-8 pb-2">
                <NoteTitle />
                <h2 className="text-xl font-bold text-gray-600 mt-2">By {note.user.name}</h2>
              </header>
              <main>
                <BlockList blocks={note.content} />
              </main>
            </div>
        </div>
        </NoteEditorContextProvider>
     );
}
 
export default NoteView;