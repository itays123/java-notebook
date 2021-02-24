import { Redirect } from "react-router-dom";
import { useAuthContext } from "./AuthContext";

const AuthOnly = ({ shouldBeAuthenticated = true, redirect, children }) => {
    const { isAuth } = useAuthContext();
    if (isAuth === shouldBeAuthenticated) return <>{children}</>
    else if (redirect) return <Redirect to={redirect} />;
    else return null;
}
 
export default AuthOnly;