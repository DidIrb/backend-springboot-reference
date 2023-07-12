package com.manytomany.manytomany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manytomany.manytomany.models.Tutorial;




public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  List<Tutorial> findByTitleContaining(String title);

  List<Tutorial> findTutorialsByTagsId(Long tagId);

  List<Tutorial> findByPublished(boolean published);
}