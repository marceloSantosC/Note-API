package com.marcelosantosc.notes.model.dao;

import com.marcelosantosc.notes.model.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteDao {


    Note getById(Long id);

    List<Note> getAll();

    Optional<Note> update(Note note);

    Optional<Note> save(Note note);

    void delete(Long id);

    Optional<Note> getLastNote();

    Optional<List<Note>> getByTittle(String titulo);
}
