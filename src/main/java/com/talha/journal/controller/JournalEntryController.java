//package com.talha.journal.controller;
//
//
//import com.talha.journal.entity.JournalEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal") // it will add mapping on whole class like we need to type this
//// first and then further methods inside the journal entry contoller class
//public class JournalEntryController {
//    private Map<Long, JournalEntity> journalEntries = new HashMap<>();
//
//    // methods inside controller class should be public so that spring can use them
//    // if we type get, it will come here
//    // only 1 get should be there as according to journal specified
//    @GetMapping
//    public List<JournalEntity> getAll( ){
//        return new ArrayList<>(journalEntries.values());
//
//    }
//    // if we type post, it will come here
//    @PostMapping
//    public boolean createJournal(@RequestBody JournalEntity one){
//        journalEntries.put(one.getJournalId(), one);
//        return  true;
//
//    }
//
//    // path variable, request parameter
//    // journal/id/abc --> abc path variable
//    // journal/id?abc=data,, then it is request parameter
//    @GetMapping("id/{myId}")
//    public JournalEntity getJournal(@PathVariable Long myId){
//        return journalEntries.get(myId);
//
//    }
//
//    @DeleteMapping("id/{myId}")
//    public JournalEntity deleteJournal(@PathVariable Long myId){
//        return journalEntries.remove(myId);
//
//    }
//
//    // update
//
//    @PutMapping("id/{myId}")
//    public boolean updateJournal(@PathVariable Long myId, @RequestBody JournalEntity one){
//        journalEntries.put(myId, one);
//        return  true;
//
//    }
//}
