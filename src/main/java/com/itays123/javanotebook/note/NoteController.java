package com.itays123.javanotebook.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteTitleAndId> getUserNotes() {
        return noteService.getUserNotes(1L);
    }

    @GetMapping(path = "/{id}")
    public Note getNoteById(@PathVariable("id") Long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public Note insertNote(@RequestBody Note note) {
        return noteService.insertNote(note);
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
