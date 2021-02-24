import { useAuthContext } from "../auth/AuthContext";
import NoteList from "./NoteList/NoteList";

const Home = () => {
    const { user } = useAuthContext();
    return ( 
        <div className="home scrollable">
            <div className="container mx-auto pt-8">
                <header className="font-bold text-5xl text-gray-800">{user.name}'s Insights</header>
                <main>
                    <NoteList notes={user.notes} />
                </main>
            </div>
        </div>
     );
}
 
export default Home;