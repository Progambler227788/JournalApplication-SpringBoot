package com.talha.journal.service;

import com.talha.journal.entity.User;
import com.talha.journal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
// we use mock tests as our real db is constantly getting changed so we test it on mock data (dummy data)
// for consistency and fast testing
// when I use spring boot test then we need MockBean otherwise we can do that with Mock simple

@SpringBootTest // we need this because our autowired annotation properties will be null if we don't use it
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImp userDetailsServiceImp;
//
//    @Autowired if we use Spring boot test
//    private UserDetailsServiceImp userDetailsServiceImp;


    @Mock
    private UserRepository userRepository;

//
//    @MockBean if we use Spring boot test
//    private UserRepository userRepository;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

//    @Disabled
    @Test
    void loadUserByUserNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("talha").password("talhais").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsServiceImp.loadUserByUsername("Talha");
        Assertions.assertNotNull(user);
    }
}
