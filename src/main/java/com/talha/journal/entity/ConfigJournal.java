package com.talha.journal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// automatically adds getter and setters in my spring boot project
// Lombok is library through which I do it

@Document(collection = "config_journal_app")

// generates code for us at compile time like we used @Data anotation
@Data
// provides ---> see below
// @Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
//@ToString
//@Builder
@NoArgsConstructor
public class ConfigJournal {

    private  String key;
    private  String value;


}
