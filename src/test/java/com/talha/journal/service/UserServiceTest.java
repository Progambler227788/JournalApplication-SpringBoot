package com.talha.journal.service;

import com.talha.journal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;


    // source to test functions --> enum source, value source
    // value-source, arguments-source


    public void testFindUserByName(){
        assertNotNull(userRepository.findByUserName("talha1"));
    }
}
