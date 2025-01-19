package com.talha.journal.service;

import com.talha.journal.entity.JournalEntity;
import com.talha.journal.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntityService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveJournal(JournalEntity journalObject){
        journalEntryRepository.save(journalObject);
    }

    public List<JournalEntity> findJournals()
    {
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> getJournalById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteJournalById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }



}
