package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dao.UserDAO;
import com.backend.model.User;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public boolean saveUser(User user) {
        User dbValue = userDAO.getByUsername(user.getUsername());
        if (dbValue != null || user.getUsername() == null || user.getPassword() == null || user.getEmailId() == null) {
            return false;
        }
        userDAO.save(user);
        return true;
    }

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    public boolean isValidUser(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return false;
        }
        User dbValue = userDAO.getByUsername(user.getUsername());
        return dbValue != null && dbValue.getPassword().equals(user.getPassword());
    }

    public User getByUsername(String username) {
        return this.userDAO.getByUsername(username);
    }
}
