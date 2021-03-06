import { useHistory } from "react-router-dom";
import { useAuthContext } from "../../auth/AuthContext";
import BlockList from "../../block/BlockList";
import NoteEditorContextProvider from "../edit/NoteEditorContext";
import NoteTitle from "../view/NoteTitle";
import { useCreateNote } from "./useCreateNote";

const CreateNote = () => {
    const { user } = useAuthContext();
    const { push } = useHistory();
    const createNote = useCreateNote();

    return ( 
        <NoteEditorContextProvider
            saveNote={createNote}
            deleteNote={() => push('/')}>
            <div className="note scrollable">
            <div className="container mx-auto">
              <header className="pt-8 pb-2">
                <NoteTitle />
                <h2 className="text-xl font-bold text-gray-600 mt-2">By {user.name}</h2>
              </header>
              <main>
                <BlockList />
              </main>
            </div>
        </div>
        </NoteEditorContextProvider>
     );
}
 
export default CreateNote;