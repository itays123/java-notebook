import { createContext, useContext } from "react";
import { useFetch } from "../shared/http/useFetch";

const AuthContext = createContext();

export function useAuthContext() {
    return useContext(AuthContext);
}

const AuthContextProvider = ({ children }) => {
    const { data, isLoading, status, doFetch } = useFetch('/api/auth');

    return ( 
        <AuthContext.Provider value={{
            user: data,
            isAuth: status === 200,
            refresh() { doFetch() },
            isLoading,
            logout() {}
        }}>
            {children}
        </AuthContext.Provider>
     );
}
 
export default AuthContextProvider;