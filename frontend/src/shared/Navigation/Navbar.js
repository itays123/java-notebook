import SignedInLinks from "./SignedInLinks"
import SignedOutLinks from "./SignedOutLinks"

const Navbar = () => {
    return ( 
        <nav className="w-full h-full bg-cream">
            <div className="container px-2 md:px-0 mx-auto flex items-center h-full">
                <div className="flex-grow logo text-md md:text-2xl font-bold text-gray-800">
                    Java Notebook
                </div>
                <div className="links h-full flex items-stretch">
                    <SignedInLinks />
                    <SignedOutLinks />
                </div>
            </div>
        </nav>
     );
}
 
export default Navbar;