package com.notes_app.notes_app.service;

import com.notes_app.notes_app.model.Note;
import com.notes_app.notes_app.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public List<Note> getAllNotes() {
        return notesRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return notesRepository.findById(id);
    }

    public Note createNote(Note note) {
        return notesRepository.save(note);
    }

    public Note updateNote(Long id, Note noteDetails) {
        Optional<Note> optionalNote = notesRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setTitle(noteDetails.getTitle());
            note.setContent(noteDetails.getContent());
            return notesRepository.save(note);
        }
        return null;
    }

    public boolean deleteNote(Long id) {
        if (notesRepository.existsById(id)) {
            notesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}