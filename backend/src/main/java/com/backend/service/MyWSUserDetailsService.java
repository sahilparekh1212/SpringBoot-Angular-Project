package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.backend.dto.MyWSUserDetailsDTO;
import com.backend.model.User;

@Component
public class MyWSUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDatabase = userService.getByUsername(username);
        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new MyWSUserDetailsDTO(userFromDatabase);
    }

}
