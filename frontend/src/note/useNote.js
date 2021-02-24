export function useNote(noteId) {
    return {
        id: noteId,
        title: "A Note",
        content: [
            { type: "H1", id: 1, content: "A Heading" },
            { type: "H2", id: 2, content: "A Quite Smaller One" },
            { type: "H3", id: 3, content: "A Much Smaller One" },
            { type: "P", id: 4, content: "A Paragraph" },
        ],
        user: { name: 'Itay', id: 1 }
    }
}