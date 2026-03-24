package com.flexira.notesapp.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
@Document(collection = "user")
public class User {
    @Id
    private String userId;
    private String username;
    private String email;

}
