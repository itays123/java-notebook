package com.itays123.javanotebook.note;

import com.itays123.javanotebook.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    private boolean isNoteMatchesSubject(Note note, String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    if(note.getUser() == null) return false;
                    else return note.getUser().getId().equals(user.getId());
                })
                .orElse(false);
    }

    public Note getNoteById(Long id, String subject) {
        return noteRepository.findById(id)
                .filter(note -> isNoteMatchesSubject(note, subject))
                .orElseThrow(() -> {throw new NoteNotFoundException();});
    }

    public Note insertNote(Note note) {
        if (note.getTitle() == null || note.getTitle().isBlank()) {
            note.setTitle("Untitled Note");
        }
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note newNote) {
        return noteRepository.findById(id)
                .map(note -> {
                    note.setTitle(newNote.getTitle());
                    note.setContent(newNote.getContent());
                    return noteRepository.save(note);
                })
                .orElseThrow(() -> {throw new NoteNotFoundException(); });
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
