package com.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Team;
import com.backend.model.User;

@Repository
public interface TeamDAO extends JpaRepository<Team, Long> {
    public User getById(long id);
}
