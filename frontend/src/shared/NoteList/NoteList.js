import NoteItem from "./NoteItem";

const NoteList = ({notes}) => {
    return (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-8">
            { notes.map(note => <NoteItem {...note} key={note.id} />) }
        </div>
    );
}
 
export default NoteList;