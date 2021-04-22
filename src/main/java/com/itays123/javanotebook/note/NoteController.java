package com.itays123.javanotebook.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * The note controller
 */
@RestController
@RequestMapping(path = "api/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Gets a specific note
     * @param request the user request data
     * @param id the id of the note
     * @return the note, secured.
     */
    @GetMapping(path = "/{id}")
    public SecureNote getNoteById(HttpServletRequest request, @PathVariable("id") Long id) {
        String subject = request.getUserPrincipal().getName();
        return noteService.getNoteById(id, subject);
    }

    /**
     * Creates a note
     * @param request the user request data
     * @param note the note to add
     * @return the note, secured.
     */
    @PostMapping
    public SecureNote insertNote(HttpServletRequest request, @RequestBody Note note) {
        String subject = request.getUserPrincipal().getName();
        return noteService.insertNote(note, subject);
    }

    /**
     * Updates a note
     * @param request the request data
     * @param id the id of the note to update
     * @param updateableNote the changes made
     * @return the updated note
     */
    @PutMapping(path = "/{id}")
    public SecureNote updateNote(HttpServletRequest request, @PathVariable("id") Long id, @RequestBody UpdateableNote updateableNote) {
        String subject = request.getUserPrincipal().getName();
        return noteService.updateNote(id, updateableNote, subject);
    }

    /**
     * Deletes a note
     * @param request the request data
     * @param id the id of the note to delete
     */
    @DeleteMapping(path = "/{id}")
    public void deleteNote(HttpServletRequest request, @PathVariable("id") Long id) {
        String subject = request.getUserPrincipal().getName();
        noteService.deleteNote(id, subject);
    }
}
