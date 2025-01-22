package com.talha.journal.service;

import com.talha.journal.entity.JournalEntity;
import com.talha.journal.entity.User;
import com.talha.journal.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntityService {

    @Autowired
    private UserEntityService userService;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    // if i've reference of journal in user but it is not in actual journal
    // if i do post new journal, it will remove null values, spring will handle it

    // in order to ensure consistancy like it may save journal but does not update users
    // to ensure consistancy, we will use transaction concept
    // If any case it fails to add in user, it will rollback.
    // if anything fail, then it is fail,
    // Only success -> single operation

    // automicity
    // isolation -> let say two users access save journal at a time
    // so both will be isolated separately


    @Transactional
    public void saveJournal(JournalEntity journalObject, String username){

        try {
            User user = userService.findByUserName(username);
            JournalEntity savedJournal = journalEntryRepository.save(journalObject);
            // add journal for user
            user.getJournalEntities().add(savedJournal);
            // now save the user
            userService.saveUser(user);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("An error occured in operation");
        }
    }

    public void saveJournal(JournalEntity journalObject){
//        User user = userService.findByUserName(username);
//        JournalEntity savedJournal = journalEntryRepository.save(journalObject);
//        // add journal for user
//        user.getJournalEntities().add(savedJournal);
        // now save the user
        journalEntryRepository.save(journalObject);
//        JournalEntityService.saveJournal(journalObject);
    }

    public List<JournalEntity> findJournals()
    {
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> getJournalById(ObjectId id){
        return journalEntryRepository.findById(id);
    }



    @Transactional
    public boolean deleteJournalById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntities().removeIf(x -> x!=null && x.getJournalId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }



}
