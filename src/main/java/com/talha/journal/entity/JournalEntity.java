package com.talha.journal.entity;

public class JournalEntity {
    private  long journalId;
    private  String journalContent;
    private  String journalTitle;

    public JournalEntity(long journalId, String journalContent, String journalTitle){
        this.journalId = journalId;
        this.journalContent = journalContent;
        this.journalTitle = journalTitle;
    }

    public String getJournalContent() {
        return journalContent;
    }

    public void setJournalContent(String journalContent) {
        this.journalContent = journalContent;
    }

    public long getJournalId() {
        return journalId;
    }

    public void setJournalId(long journalId) {
        this.journalId = journalId;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }
}
