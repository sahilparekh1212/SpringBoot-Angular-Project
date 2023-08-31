package com.backend.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.BackendApplication;
import com.backend.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class UserDAOTests {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void findAllUsersTest() {
        List<User> dbList = userDAO.findAll();
        User userA = new User("aUser", "aPassword", "aUser@user.com");
        User userB = new User("bUser", "bPassword", "bUser@user.com");
        userA = userDAO.save(userA);
        userB = userDAO.save(userB);
        List<User> dbList2 = userDAO.findAll();
        assertEquals(dbList2.size() - dbList.size(), 2);
        userDAO.delete(userA);
        userDAO.delete(userB);
    }

    @Test
    public void saveUserTest() {
        User user = new User("aUser", "aPassword", "aUser@user.com");
        user = userDAO.save(user);
        assertTrue((user.getId()).longValue() > 0);
        userDAO.delete(user);
    }

    @Test
    public void getByIdUserTest() {
        User user = new User("aUser", "aPassword", "aUser@user.com");
        User expected = userDAO.save(user);
        User actual = userDAO.getById((long) expected.getId());
        assertNotNull(actual);
        userDAO.delete(expected);
    }

    @Test
    public void getByIdUsernameUserTest() {
        User user = new User("aUser", "aPassword", "aUser@user.com");
        User expected = userDAO.save(user);
        User actual = userDAO.getByUsername(expected.getUsername());
        assertNotNull(actual);
        userDAO.delete(expected);
    }

    @Test
    public void deleteUserTest() {
        User user = new User("aUser", "aPassword", "aUser@user.com");
        User expected = userDAO.save(user);
        userDAO.delete(expected);
        assertFalse(userDAO.findById(expected.getId()).isPresent());
    }

}