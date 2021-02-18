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
    public List<NoteTitleAndId> getSlimNotes() {
        return noteService.getSlimNotes();
    }

    @GetMapping(path = "/{id}")
    public Note getNoteById(@PathVariable("id") Long id) {
        return noteService.getNoteById(id);
    }
}
