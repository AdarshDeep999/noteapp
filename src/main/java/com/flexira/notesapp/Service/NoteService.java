package com.flexira.notesapp.Service;

import com.flexira.notesapp.Model.Note;
import com.flexira.notesapp.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class NoteService {

    @Autowired
    private NoteRepository noterepository;

    public Note createNote(Note note){
        if(note.getTitle() == null || note.getContent()==null){
            throw new RuntimeException("Both title and content required");
        }
        return noterepository.save(note);
    }

    public List<Note> getUserNote(String userId){
        if(!noterepository.existsByUserId(userId)){
            throw new RuntimeException("User not found!");
        }
        return noterepository.findByUserId(userId);
    }

    public String removeNote(String id){
        if(!noterepository.existsById(id)) {
            throw new RuntimeException("Note with this ID doesnt exist!");
        }
        noterepository.deleteById(id);
        return "Note Deleted!";

    }

    public Note updateNote(String id,Note note){
        note.setId(id);
        return noterepository.save(note);
    }

    public List<Note> getAllNote(){
        return noterepository.findAll();
    }


}
