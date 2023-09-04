package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.dao.UserDAO;
import com.backend.model.User;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public boolean saveUser(User user) {
        if (user == null) {
            return false;
        }
        User dbValue = userDAO.getByUsername(user.getUsername());
        if (dbValue != null || user.getUsername() == null || user.getPassword() == null || user.getEmailId() == null) {
            return false;
        }
        if (user.getRoles() == null) {
            user.setRoles("ROLE_user");
        }
        user.setPassword(this.getEncodedString(user.getPassword()));
        userDAO.save(user);
        return true;
    }

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    public User getByUsername(String username) {
        return this.userDAO.getByUsername(username);
    }

    private String getEncodedString(String input) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);
        return encoder.encode(input);
    }
}
