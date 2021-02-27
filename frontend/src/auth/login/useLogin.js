import { useFetch } from "../../shared/http/useFetch"
import { useAuthContext } from "../AuthContext";

export function useLogin() {
    const { refresh } = useAuthContext();
    const { doFetch } = useFetch('/api/auth/login', 'POST', false, false);

    return {
        login(email, password) {
            doFetch({ email, password })
                .then(res => res.status === 200 && refresh());
        }
    }
}