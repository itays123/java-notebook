import AuthOnly from "../../auth/AuthOnly";
import NavItem from "./NavItem";
import { useLogout } from "./useLogout";

const SignedInLinks = () => {
    const { logout } = useLogout()
    return ( 
        <AuthOnly>
            <NavItem to='/'>My Notes</NavItem>
            <NavItem to='/create'>New Note</NavItem>
            <button onClick={() => logout()} className="flex items-center px-1 md:px-2 hover:bg-cream-dark">Log Out</button>
        </AuthOnly>
     );
}
 
export default SignedInLinks;