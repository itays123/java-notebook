import { useFetch } from "../../shared/http/useFetch"

export function useSaveNote(id) {
    const { doFetch } = useFetch(`/api/note/${id}`, 'PUT', false);
    return updateable => {
        doFetch(updateable);
    }
}