package com.talha.journal.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entities")
public class JournalEntity {

    @Id
    // take ObjectId type
    private ObjectId journalId;
    private  String journalContent;
    private  String journalTitle;

    private LocalDateTime date;

//    public JournalEntity( String  journalId, String journalContent, String journalTitle, Date date){
//        this.journalId = journalId;
//        this.journalContent = journalContent;
//        this.journalTitle = journalTitle;
//        this.date = date;
//    }

    public LocalDateTime  getDate() {
        return date;
    }

    public void setDate(LocalDateTime  date) {
        this.date = date;
    }

    public String getJournalContent() {
        return journalContent;
    }

    public void setJournalContent(String journalContent) {
        this.journalContent = journalContent;
    }

    public ObjectId getJournalId() {
        return journalId;
    }

    public void setJournalId( ObjectId journalId) {
        this.journalId = journalId;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }
}
