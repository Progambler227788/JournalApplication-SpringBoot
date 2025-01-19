package com.talha.journal.controller;



import com.talha.journal.entity.JournalEntity;
import com.talha.journal.service.JournalEntityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal") // it will add mapping on whole class like we need to type this
// first and then further methods inside the journal entry contoller class
public class JournalEntryControllerV1 {
//    private Map<Long, JournalEntity> journalEntries = new HashMap<>();

    // methods inside controller class should be public so that spring can use them
    // if we type get, it will come here
    // only 1 get should be there as according to journal specified

    @Autowired  // dependency injection
    private JournalEntityService journalEntityService;


    @GetMapping
    public List<JournalEntity> findJournals( ){
        return journalEntityService.findJournals();

    }
    // if we type post, it will come here
    @PostMapping
    public JournalEntity createJournal(@RequestBody JournalEntity one){
        one.setDate(LocalDateTime.now());
        //  journalEntries.put(one.getJournalId(), one);
        journalEntityService.saveJournal(one);
        return  one;

    }

    // path variable, request parameter
    // journal/id/abc --> abc path variable
    // journal/id?abc=data,, then it is request parameter
    @GetMapping("id/{myId}")
    public JournalEntity getJournal(@PathVariable ObjectId myId){
        // optional is like may be entity is there or not
        return journalEntityService.getJournalById(myId).orElse(null);

    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournal(@PathVariable ObjectId  myId){
        journalEntityService.deleteJournalById(myId);
        return  true;

    }

    // update

    @PutMapping("id/{myId}")
    public JournalEntity updateJournal(@PathVariable ObjectId  myId, @RequestBody JournalEntity one){
        //     journalEntries.put(myId, one);
        JournalEntity journalEntity = journalEntityService.getJournalById(myId).orElse(null);
        if (journalEntity!=null){
            journalEntity.setJournalTitle(one.getJournalTitle()!=null && !one.getJournalTitle().equals("") ? one.getJournalTitle() : journalEntity.getJournalTitle());

            journalEntity.setJournalContent(one.getJournalContent()!=null && !one.getJournalContent().equals("") ? one.getJournalContent() : journalEntity.getJournalContent());

        }


        journalEntityService.saveJournal(journalEntity);
        return  one;

    }
}
