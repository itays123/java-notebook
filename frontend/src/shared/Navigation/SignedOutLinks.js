import AuthOnly from "../../auth/AuthOnly";
import NavItem from "./NavItem";

const SignedOutLinks = () => {
    return ( 
        <AuthOnly shouldBeAuthenticated={false}>
            <NavItem to='/login'>Log In</NavItem>
            <NavItem to='/register'>Register</NavItem>
        </AuthOnly>
     );
}
 
export default SignedOutLinks;