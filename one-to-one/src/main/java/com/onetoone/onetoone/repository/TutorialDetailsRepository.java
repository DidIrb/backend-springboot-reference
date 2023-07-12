package com.onetoone.onetoone.repository;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onetoone.onetoone.models.TutorialDetails;


@Repository
public interface TutorialDetailsRepository extends JpaRepository<TutorialDetails, Long> {
  @Transactional
  void deleteById(long id);
  
  @Transactional
  void deleteByTutorialId(long tutorialId);
}