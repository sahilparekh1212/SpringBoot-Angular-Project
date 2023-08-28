package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
