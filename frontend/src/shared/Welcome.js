import { Link } from "react-router-dom"

const Welcome = () => {
    return ( 
        <div className="welcome container mx-auto h-full px-4 md:px-0">
            <header className="mt-12">
                <h1 className="text-5xl font-bold text-cream-darker">Java Notebook</h1>
                <h2 className="mt-2 text-3xl font-bold text-cream-dark">A Place to Keep Your Insights!</h2>
                <p className="mt-2 text-lg">With an outstanding user interface, we can help you keep anything you need!</p>
            </header>
            <section className="actions grid gird-cols-1 md:grid-cols-3 w-full md:w-3/4 lg:w-2/3 max-w-lg mt-6 gap-4">
                <Link to="/login" className="bg-cream text-center rounded py-3 px-2 font-bold text-gray-800">Log In</Link>
                <Link to="/register" className="bg-cream-lightish text-center rounded py-3 px-2 font-bold text-gray-800">Register</Link>
            </section>
        </div>
     );
}
 
export default Welcome;