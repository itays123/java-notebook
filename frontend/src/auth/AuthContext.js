import { createContext, useContext } from "react";

const AuthContext = createContext();

export function useAuthContext() {
    return useContext(AuthContext);
}

const AuthContextProvider = ({ children }) => {
    const dummyData = {
        isAuth: true,
        user: {
            name: 'Itay',
            notes: [
                { title: 'My First Note', id: 1 },
                { title: 'Another Note', id: 2 },
                { title: 'Another Note #2', id: 3 },
                { title: 'Another Note #3', id: 4 },
                { title: 'Untitled Note', id: 5 },
                { title: 'ahjfnfjff', id: 6 },
                { title: 'A Secret Note', id: 7 },
            ]
        },
        refresh() {},
        logout() {}
    }
    return ( 
        <AuthContext.Provider value={dummyData}>{children}</AuthContext.Provider>
     );
}
 
export default AuthContextProvider;