package com.notes_app.notes_app.controller;

import com.notes_app.notes_app.model.Note;
import com.notes_app.notes_app.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @GetMapping
    public List<Note> getAllNotes() {
        return notesService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> note = notesService.getNoteById(id);
        return note.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return notesService.createNote(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        Note updatedNote = notesService.updateNote(id, noteDetails);
        return updatedNote != null ? ResponseEntity.ok(updatedNote) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        boolean deleted = notesService.deleteNote(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}