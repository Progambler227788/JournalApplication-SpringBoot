package com.talha.journal.service;

import com.talha.journal.entity.JournalEntity;
import com.talha.journal.entity.User;
import com.talha.journal.repository.JournalEntryRepository;
import com.talha.journal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserEntityService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntityService.class);

//    @Lazy
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User object){
        try {
            object.setPassword(passwordEncoder.encode(object.getPassword()));
            object.setRoles(Arrays.asList("USER"));
            userRepository.save(object);
        }

        catch (Exception e){
            // by default print --> error, warn, info
            // for debug and trace we need customization
            // if we use sl4j annotation then use log object
//            logger.info("Error occurred in saving user with user-name --> {}", object.getUserName(), e);
            log.info("Error occurred in saving user with user-name --> {}", object.getUserName(), e);
        }
    }

    public void saveAdminUser(User object){
        object.setPassword(passwordEncoder.encode(object.getPassword()));
        object.setRoles(Arrays.asList("ADMIN"));
        userRepository.save(object);
    }

    public void saveUser(User object){
        userRepository.save(object);
    }

    public List<User> findUsers()
    {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id){
        return  userRepository.findById(id);
    }

    public void deleteUserById(ObjectId id){
        userRepository.deleteById(id);
    }

    public void deleteUserByName(String username){
        userRepository.deleteByUserName(username);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
