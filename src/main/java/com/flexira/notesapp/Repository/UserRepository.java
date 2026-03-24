package com.flexira.notesapp.Repository;

import com.flexira.notesapp.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    void deleteByUsername(String username);
    Boolean existsByUsername(String username);
}
