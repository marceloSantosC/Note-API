package com.marcelosantosc.notes.controller;


import com.marcelosantosc.notes.model.entity.Note;
import com.marcelosantosc.notes.model.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteController {


    private NoteService service;

    @Autowired
    public void setService(NoteService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Note> getById(@PathVariable("id") Long id) {
            return ResponseEntity.ok(service.getById(id));
    }

    @RequestMapping(params = "tittle", method = RequestMethod.GET)
    public ResponseEntity<List<Note>> getByTittle(@RequestParam String tittle) {
        return ResponseEntity.ok(service.getByTittle(tittle));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> save(@RequestBody Note note) {
        try {
            Note savedNote = service.save(note);
            return ResponseEntity.ok(savedNote);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> update(@RequestBody Note note, @PathVariable("id")  Long id) {
        note.setId(id);
        return ResponseEntity.ok(service.update(note));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        try {
            service.delete(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Note>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
