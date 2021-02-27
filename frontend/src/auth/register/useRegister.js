import { useFetch } from "../../shared/http/useFetch";
import { useAuthContext } from "../AuthContext";

export function useRegister() {
    const { refresh } = useAuthContext();
    const { doFetch } = useFetch('/api/auth/register', 'POST', false, false);

    return {
        register(name, email, password) {
            doFetch({ name, email, password })
                .then(res => res.status === 200 && refresh())
        }
    }
}