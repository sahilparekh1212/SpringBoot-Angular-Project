package com.backend.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.BackendApplication;
import com.backend.dao.UserDAO;
import com.backend.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class UserServiceTests {

    @InjectMocks
    UserService userService;

    @Mock
    private UserDAO userDAO;

    @Test
    public void findAllUsersTest() {
        List<User> daoRes = new ArrayList<>();
        daoRes.add(new User("aUser", "aPassword", "a@a.com"));
        when(userDAO.findAll()).thenReturn(daoRes);
        assertEquals(userService.findAllUsers(), daoRes);
    }

    @Test
    public void saveUserTest() {
        User daoRes = new User("aUser", "aGame", "a@a.com");
        daoRes.setId(1L);
        when(userDAO.getByUsername("b")).thenReturn(null);
        when(userDAO.getByUsername("aUser")).thenReturn(daoRes);
        assertEquals(userService.saveUser(null), false);
        assertEquals(userService.saveUser(daoRes), false);
        assertEquals(userService.saveUser(new User(null, "c", "c")), false);
        assertEquals(userService.saveUser(new User("c", null, "c")), false);
        assertEquals(userService.saveUser(new User("c", "c", null)), false);
        assertEquals(userService.saveUser(new User("b", "b", "b")), true);
    }

    @Test
    public void isValidUserTest() {
        User daoRes = new User("aUser", "aPassword", "a@a.com");
        daoRes.setId(1L);
        when(userDAO.getByUsername("aUser")).thenReturn(daoRes);
        assertEquals(userService.isValidUser(null), false);
        assertEquals(userService.isValidUser(new User(null, "c", "c")), false);
        assertEquals(userService.isValidUser(new User("c", null, "c")), false);
        assertEquals(userService.isValidUser(new User("c", "c", null)), false);
        assertEquals(userService.isValidUser(daoRes), true);
    }

    @Test
    public void getByUsernameTest() {
        User daoRes = new User("a", "b", "c");
        when(userDAO.getByUsername(Mockito.anyString())).thenReturn(daoRes);
        assertEquals(userService.getByUsername(Mockito.anyString()), daoRes);
    }

}
