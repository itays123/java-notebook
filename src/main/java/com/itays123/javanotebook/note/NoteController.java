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
    public Note getNoteById(HttpServletRequest request, @PathVariable("id") Long id) {
        String subject = request.getUserPrincipal().getName();
        return noteService.getNoteById(id, subject);
    }

    @PostMapping
    public Note insertNote(HttpServletRequest request, @RequestBody Note note) {
        String subject = request.getUserPrincipal().getName();
        return noteService.insertNote(note, subject);
    }

    @PutMapping(path = "/{id}")
    public Note updateNote(@PathVariable("id") Long id, @RequestBody Note note) {
        return noteService.updateNote(id, note);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
    }
}
