package com.flexira.notesapp.Service;

import com.flexira.notesapp.Model.Note;
import com.flexira.notesapp.Model.User;
import com.flexira.notesapp.Repository.NoteRepository;
import com.flexira.notesapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public String removeUser(String username){
        if(!userRepository.existsByUsername(username)){
            throw new RuntimeException("User not found!");
        }
        List<Note> tempNotes = noteRepository.findByUsername(username);
        for(Note note:tempNotes){
            String id = note.getId();
            noteRepository.deleteById(id);
        }
        userRepository.deleteByUsername(username);
        return "User deleted!";
    }

}
