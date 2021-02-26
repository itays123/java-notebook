export function useCreateNote() {
    return updateable => {
        console.log({
            title: updateable.title,
            content: updateable.addedBlocks
        })
    }
}