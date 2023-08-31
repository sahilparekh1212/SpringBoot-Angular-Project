package com.backend.dao;

import org.springframework.stereotype.Repository;

import com.backend.model.User;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    public User save(User user);

    public List<User> findAll();

    public User getById(long id);

    public User getByUsername(String username);

    public void delete(User user);

}
