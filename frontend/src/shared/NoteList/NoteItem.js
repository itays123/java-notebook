import { Link } from "react-router-dom";

const NoteItem = ({ title, id }) => {
    return ( 
        <Link to={`/note/${id}`} className="bg-cream-lightish p-6 shadow text-lg">
            {title}
        </Link>
     );
}
 
export default NoteItem;