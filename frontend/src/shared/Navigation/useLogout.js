import { useAuthContext } from "../../auth/AuthContext";
import { useFetch } from "../http/useFetch";

export function useLogout() {
    const { doFetch } = useFetch('/api/auth', 'DELETE', false, false);
    const { refresh } = useAuthContext();
    return {
        logout() {
            doFetch()
                .then(res => res.status === 200 && refresh())
        }
    }
}