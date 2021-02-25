import { useAuthContext } from "../../auth/AuthContext";
import AuthOnly from "../../auth/AuthOnly";
import NavItem from "./NavItem";

const SignedInLinks = () => {
    const { logout } = useAuthContext();
    return ( 
        <AuthOnly>
            <NavItem to='/'>My Notes</NavItem>
            <NavItem to='/create'>New Note</NavItem>
            <a href="/welcome" onClick={() => logout()} className="flex items-center px-1 md:px-2 hover:bg-cream-dark">Log Out</a>
        </AuthOnly>
     );
}
 
export default SignedInLinks;