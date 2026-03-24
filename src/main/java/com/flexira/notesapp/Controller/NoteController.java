package com.flexira.notesapp.Controller;

import com.flexira.notesapp.Model.Note;
import com.flexira.notesapp.Repository.NoteRepository;
import com.flexira.notesapp.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteservice;

    @PostMapping
    public Note createNote(@RequestBody Note note){

        return noteservice.createNote(note);
    }

    @GetMapping("/{userId}")
    public List<Note> getUserNote(@PathVariable String userId){

        return noteservice.getUserNote(userId);
    }

    @DeleteMapping("/{id}")
    public String removeNote(@PathVariable String id){
        return noteservice.removeNote(id);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note note){
        return noteservice.updateNote(id,note);
    }

    @GetMapping
    public List<Note> getAllNote(){
        return noteservice.getAllNote();
    }

}
