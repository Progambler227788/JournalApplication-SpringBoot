package com.talha.journal.service;

import com.talha.journal.entity.User;
import com.talha.journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImp implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUserName(username);
        if (u!=null){

            UserDetails userDetails =  org.springframework.security.core.userdetails.User.builder().
             username(u.getUserName()).
             password(u.getPassword()).
             roles(u.getRoles().toArray(new String[0]))
             .build();
     // example:
     // u.getRoles() returns ["ADMIN", "USER"].
     // [{"ADMIN", "USER"}

            return  userDetails;

        }
        throw new UsernameNotFoundException("Given user not found with this username --> "+username);

    }
}
