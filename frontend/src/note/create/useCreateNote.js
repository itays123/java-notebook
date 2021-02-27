import { useFetch } from "../../shared/http/useFetch"

export function useCreateNote() {
    const { doFetch } = useFetch('/api/note', 'POST', false);
    return updateable => {
        doFetch({
            title: updateable.title,
            content: updateable.addedBlocks
        })
    }
}