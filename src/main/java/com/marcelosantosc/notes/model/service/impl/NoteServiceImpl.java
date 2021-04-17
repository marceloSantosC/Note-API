package com.marcelosantosc.notes.model.service.impl;

import com.marcelosantosc.notes.model.dao.NoteDao;
import com.marcelosantosc.notes.model.entity.Note;
import com.marcelosantosc.notes.model.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteDao dao;

    @Autowired
    private void setDao(NoteDao dao) {
        this.dao = dao;
    }


    @Override
    public List<Note> getAll() {
        return this.dao.getAll();
    }

    @Override
    public Note getLastNote() {
        return this.dao.getLastNote().orElse(null);
    }

    @Override
    public Note getById(Long id) {
        return this.dao.getById(id);
    }

    @Override
    public void delete(Long id) {
        this.dao.delete(id);
    }

    @Override
    public Note save(Note note) {
        note.setDate(new Date());
        return this.dao.save(note).orElse(note);
    }

    @Override
    public Note update(Note note) {
        note.setDate(new Date());
        return this.dao.update(note).orElse(note);
    }

    @Override
    public List<Note> getByTittle(String tittle) {
        return this.dao.getByTittle(tittle).orElse(null);
    }
}
