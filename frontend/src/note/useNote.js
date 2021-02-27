import { useFetch } from "../shared/http/useFetch"

export function useNote(noteId) {
    const { status, isLoading, data } = useFetch(`/api/note/${noteId}`);
    return {
        ...data,
        isLoading,
        notFound: status !== 200 && !isLoading
    }
}