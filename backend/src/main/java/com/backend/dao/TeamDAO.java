package com.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Team;

@Repository
public interface TeamDAO extends JpaRepository<Team, Long> {

}
