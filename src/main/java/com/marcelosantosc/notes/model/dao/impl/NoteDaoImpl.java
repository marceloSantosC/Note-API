package com.marcelosantosc.notes.model.dao.impl;

import com.marcelosantosc.notes.model.dao.GenericDao;
import com.marcelosantosc.notes.model.dao.NoteDao;
import com.marcelosantosc.notes.model.entity.Note;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NoteDaoImpl extends GenericDao implements NoteDao  {


    @Override
    public Note getById(Long id) {
        return this.getEntityManager().find(Note.class, id);
    }

    @Override
    public List<Note> getAll() {

        List<Note> result = this.getEntityManager().createQuery("FROM Note", Note.class).getResultList();
        return Optional.ofNullable(result).orElse(new ArrayList<>());
    }

    @Override
    public Optional<Note> update(Note note) {
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().merge(note);
        this.getEntityManager().getTransaction().commit();
        return Optional.ofNullable(note);
    }

    @Override
    public Optional<Note> save(Note note) {
        try {
            this.getEntityManager().getTransaction().begin();
            this.getEntityManager().persist(note);
            this.getEntityManager().getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            this.getEntityManager().getTransaction().rollback();
        }
        return Optional.ofNullable(note);
    }

    @Override
    public void delete(Long id) {
        Note note = this.getById(id);
        if (note != null) {
            try {
                this.getEntityManager().getTransaction().begin();
                this.getEntityManager().remove(note);
                this.getEntityManager().getTransaction().commit();
            } catch (Exception exception) {
                this.getEntityManager().getTransaction().rollback();
            }

        }
    }

    @Override
    public Optional<Note> getLastNote() {
        String queryText = "FROM Note ORDER BY date DESC";
        TypedQuery<Note> query = this.getEntityManager()
                .createQuery(queryText, Note.class)
                .setMaxResults(1);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<List<Note>> getByTittle(String titulo) {

        String queryText = "FROM Note WHERE tittle LIKE CONCAT('%', :titulo, '%')";
        TypedQuery<Note> query = this.getEntityManager()
                .createQuery(queryText, Note.class)
                .setParameter("titulo", titulo);

        return Optional.ofNullable(query.getResultList());
    }


}
