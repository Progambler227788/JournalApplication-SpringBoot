package com.talha.journal.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;;

import java.util.ArrayList;
import java.util.List;

@Document(collection =  "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // it is need as in data class we have arguements based constructor, so for simple objects we need it
public class User {

    // use spring boot type indexed here, not other one
    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @Id
    private ObjectId id;


    // we will here having only references of journal entities here
    // working as a foreign key
    @DBRef // this is important to link user with journal entity collection
    private List<JournalEntity> journalEntities = new ArrayList<>();

    private List<String> roles;
}
