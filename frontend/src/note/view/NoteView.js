import { useParams } from "react-router-dom";
import BlockList from "../../block/view/BlockList";
import { useNote } from "../useNote";

const NoteView = () => {
    const { id } = useParams();
    const note = useNote(id);
    return ( 
        <div className="note scrollable">
            <div className="container mx-auto">
              <header className="pt-8 pb-2">
                <h1 className="text-5xl font-bold text-gray-800">{note.title}</h1>
                <h2 className="text-xl font-bold text-gray-600 mt-2">By {note.user.name}</h2>
              </header>
              <main>
                <BlockList blocks={note.content} />
              </main>
            </div>
        </div>
     );
}
 
export default NoteView;