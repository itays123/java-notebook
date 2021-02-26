import { useHistory } from "react-router-dom"

export function useDeleteNote(id) {
    const { push } = useHistory();
    return () => {
        push('/')
    }
}