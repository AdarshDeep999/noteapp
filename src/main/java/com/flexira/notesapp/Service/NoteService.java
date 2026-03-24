package com.flexira.notesapp.Service;

import com.flexira.notesapp.Model.Note;
import com.flexira.notesapp.Repository.NoteRepository;
import com.flexira.notesapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public Note createNote(Note note){
        if(note.getTitle() == null || note.getContent()==null){
            throw new RuntimeException("Both title and content required");
        }
        if(!userRepository.existsByUsername(note.getUsername())){
            throw new RuntimeException("User not found!");
        }
        return noteRepository.save(note);
    }

    public List<Note> getUserNote(String username){
        if(!userRepository.existsByUsername(username)){
            throw new RuntimeException("User not found!");
        }
        return noteRepository.findByUsername(username);
    }

    public String removeNote(String id){
        if(!noteRepository.existsById(id)) {
            throw new RuntimeException("Note with this ID doesnt exist!");
        }
        noteRepository.deleteById(id);
        return "Note Deleted!";

    }

    public Note updateNote(String id,Note note){
        note.setId(id);
        return noteRepository.save(note);
    }

    public List<Note> getAllNote(){
        return noteRepository.findAll();
    }


}
