package com.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.backend.model.User;
import com.backend.service.UserService;

@Component
public class MyWSUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDatabase = userService.getByUsername(username);
        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new MyWSUserDetailsMapper(userFromDatabase);
    }

}
