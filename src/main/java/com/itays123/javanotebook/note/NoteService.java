package com.itays123.javanotebook.note;

import com.itays123.javanotebook.user.EmailNotFoundException;
import com.itays123.javanotebook.user.User;
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

    public SecureNote getNoteById(Long id, String subject) {
        Note foundNote = noteRepository.findById(id)
                .filter(note -> isNoteMatchesSubject(note, subject))
                .orElseThrow(() -> {throw new NoteNotFoundException();});
        return SecureNote.fromNote(foundNote);
    }

    public SecureNote insertNote(Note note, String subject) {
        if (note.getTitle() == null || note.getTitle().isBlank()) {
            note.setTitle("Untitled Note");
        }
        User user = userRepository.findByEmail(subject).orElseThrow(EmailNotFoundException::new);
        note.setUser(user);
        return SecureNote.fromNote(noteRepository.save(note));
    }

    public SecureNote updateNote(Long id, UpdateableNote updateableNote, String subject) {
        Note updatedNote = noteRepository.findById(id)
                .filter(note -> isNoteMatchesSubject(note, subject))
                .map(note -> noteRepository.save(UpdateableNote.applyChanges(note, updateableNote))
                )
                .orElseThrow(() -> {throw new NoteNotFoundException(); });
        return SecureNote.fromNote(updatedNote);
    }

    public void deleteNote(Long id, String subject) {
        noteRepository.findById(id)
                .filter(note -> isNoteMatchesSubject(note, subject))
                .map(note -> {
                    noteRepository.deleteById(id);
                    return 0;
                });

    }
}
