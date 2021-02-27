import { useHistory } from "react-router-dom"
import { useFetch } from "../../shared/http/useFetch";

export function useDeleteNote(id) {
    const { doFetch } = useFetch(`/api/note/${id}`, 'DELETE', false, false);
    const { push } = useHistory();
    return () => {
        doFetch().then(() => push('/'));
    }
}