package com.starter.startercode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starter.startercode.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> { 
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}
// JPA methods have be availed to us
