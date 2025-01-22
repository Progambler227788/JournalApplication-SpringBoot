package com.talha.journal.controller;



import com.talha.journal.entity.JournalEntity;
import com.talha.journal.entity.User;
import com.talha.journal.repository.JournalEntryRepository;
import com.talha.journal.service.JournalEntityService;
import com.talha.journal.service.UserEntityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private UserEntityService userService;

//
//    @GetMapping
//    public List<JournalEntity> findJournals( ){
//        return journalEntityService.findJournals();
//
//    }


    @GetMapping()
    public ResponseEntity<?> getJournalsForUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uname = authentication.getName();

        User userIn = userService.findByUserName(uname);
        // find all journals related to user using his name
        List<JournalEntity> journals = userIn.getJournalEntities();

        if (journals!=null && !journals.isEmpty()){
            return new ResponseEntity<>(journals, HttpStatus.OK);
        }

        return new ResponseEntity<>(journals, HttpStatus.NOT_FOUND);

    }
    // if we type post, it will come here
    // check for annotations properly
    @PostMapping
    public ResponseEntity<JournalEntity> createJournal(@RequestBody JournalEntity one){


        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String uname = authentication.getName();



            one.setDate(LocalDateTime.now());
            //  journalEntries.put(one.getJournalId(), one);
            journalEntityService.saveJournal(one, uname);

            return new ResponseEntity<>(one, HttpStatus.CREATED);

        }
        catch (Exception e)
        {
 // client has entered something wrong
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

//        return  one;

    }

    // path variable, request parameter
    // journal/id/abc --> abc path variable
    // journal/id?abc=data,, then it is request parameter

    // already agr user k id add ni he , tu 400 bad request ata ha
    // already add tha delete mar dya now 404 not found
    // 1xx -> informational codes
    // 2xx -> ok, created, for deletion,,, 200-> get, 201->post, 204->delete (returns no content)
    // 4xx -> client side issue,
    // 5xx -> server side issue

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntity> getJournal(@PathVariable ObjectId myId){
        // optional is like may be entity is there or not
     //   Optional<JournalEntity> journalEntity =  journalEntityService.getJournalById(myId).orElse(null);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();


        User user = userService.findByUserName(userName);

        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getJournalId().equals(myId)).toList();

        if(! collect.isEmpty()) {

            Optional<JournalEntity> journalEntity = journalEntityService.getJournalById(myId);


            // doing if else work below
            if (journalEntity.isPresent()) {
                return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);

            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




    @DeleteMapping("id/{myId}")
    public ResponseEntity<JournalEntity> deleteJournal(@PathVariable ObjectId  myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uname = authentication.getName();
        boolean removed = journalEntityService.deleteJournalById(myId, uname );
        if (removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    // update

    // if it is not present in database then it should be not found

    // ResponseEntity<JournalEntity>  -> ResponseEntity<?>  ? alternative

    // as user already has journals objects, so we don't need user here
    // we will update only journal object and in user it will auto updated
    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntity> updateJournal(@PathVariable ObjectId  myId, @RequestBody JournalEntity one){
        //     journalEntries.put(myId, one);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uname = authentication.getName();


        User user = userService.findByUserName(uname);

        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getJournalId().equals(myId)).toList();

        if(!collect.isEmpty()) {

            Optional<JournalEntity> old = journalEntityService.getJournalById(myId);


            // doing if else work below
            if (old.isPresent()) {
                JournalEntity journalEntity = old.get();

                journalEntity.setJournalTitle(one.getJournalTitle() != null && !one.getJournalTitle().equals("") ? one.getJournalTitle() : journalEntity.getJournalTitle());

                journalEntity.setJournalContent(one.getJournalContent() != null && !one.getJournalContent().equals("") ? one.getJournalContent() : journalEntity.getJournalContent());

                journalEntityService.saveJournal(journalEntity);

                return new ResponseEntity<>(journalEntity, HttpStatus.OK);
            }


        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
