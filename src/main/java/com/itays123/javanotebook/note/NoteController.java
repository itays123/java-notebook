package com.itays123.javanotebook.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "api/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(path = "/{id}")
    public SecureNote getNoteById(HttpServletRequest request, @PathVariable("id") Long id) {
        String subject = request.getUserPrincipal().getName();
        return noteService.getNoteById(id, subject);
    }

    @PostMapping
    public SecureNote insertNote(HttpServletRequest request, @RequestBody Note note) {
        String subject = request.getUserPrincipal().getName();
        return noteService.insertNote(note, subject);
    }

    @PutMapping(path = "/{id}")
    public SecureNote updateNote(HttpServletRequest request, @PathVariable("id") Long id, @RequestBody Note note) {
        String subject = request.getUserPrincipal().getName();
        return noteService.updateNote(id, note, subject);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteNote(HttpServletRequest request, @PathVariable("id") Long id) {
        String subject = request.getUserPrincipal().getName();
        noteService.deleteNote(id, subject);
    }
}
