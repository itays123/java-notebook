package com.itays123.javanotebook.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteTitleAndId> getSlimNotes() {
        return noteRepository.findIdsAndTitles();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> {throw new NoteNotFoundException();});
    }

    public Note insertNote(Note note) {
        if (note.getTitle() == null || note.getTitle().isBlank()) {
            note.setTitle("Untitled Note");
        }
        return noteRepository.save(note);
    }
}
