package com.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Team;

@Repository
public interface TeamDAO extends JpaRepository<Team, Long> {
    public Team getById(long id);

    public Optional<Team> findById(Long id);

    public Team save(Team team);
}
