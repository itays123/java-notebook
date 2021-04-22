package com.itays123.javanotebook.note;

import com.itays123.javanotebook.user.EmailNotFoundException;
import com.itays123.javanotebook.user.User;
import com.itays123.javanotebook.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The note service
 */
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

    /**
     * Returns a specific note if created by the current user
     * @param id the id of the note
     * @param subject the user requesting the note
     * @return the note as secured.
     */
    public SecureNote getNoteById(Long id, String subject) {
        Note foundNote = noteRepository.findById(id)
                .filter(note -> isNoteMatchesSubject(note, subject))
                .orElseThrow(() -> {throw new NoteNotFoundException();});
        return SecureNote.fromNote(foundNote);
    }

    /**
     * Creates a note
     * @param note the note to create
     * @param subject the note creator
     * @return the new note, as secured
     */
    public SecureNote insertNote(Note note, String subject) {
        if (note.getTitle() == null || note.getTitle().isBlank()) {
            note.setTitle("Untitled Note");
        }
        User user = userRepository.findByEmail(subject).orElseThrow(EmailNotFoundException::new);
        note.setUser(user);
        return SecureNote.fromNote(noteRepository.save(note));
    }

    /**
     * Updates a note
     * @param id the id of the note to update
     * @param updateableNote the changes to make
     * @param subject the updating user
     * @return the updated note, as secured.
     */
    public SecureNote updateNote(Long id, UpdateableNote updateableNote, String subject) {
        Note updatedNote = noteRepository.findById(id)
                .filter(note -> isNoteMatchesSubject(note, subject))
                .map(note -> noteRepository.save(UpdateableNote.applyChanges(note, updateableNote))
                )
                .orElseThrow(() -> {throw new NoteNotFoundException(); });
        return SecureNote.fromNote(updatedNote);
    }

    /**
     * Deletes a note
     * @param id the id of the note to delete
     * @param subject the user performing the action
     */
    public void deleteNote(Long id, String subject) {
        noteRepository.findById(id)
                .filter(note -> isNoteMatchesSubject(note, subject))
                .map(note -> {
                    noteRepository.deleteById(id);
                    return 0;
                });

    }
}
