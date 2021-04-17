package com.marcelosantosc.notes.model.service;

import com.marcelosantosc.notes.model.entity.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAll();
    Note getLastNote();
    Note getById(Long id);
    void delete(Long id);
    Note save(Note note);
    Note update(Note note);
    List<Note> getByTittle(String tittle);

}
