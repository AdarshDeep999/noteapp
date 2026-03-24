package com.flexira.notesapp.Repository;

import com.flexira.notesapp.Model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note,String>{
    List<Note> findByUsername(String username);
}
