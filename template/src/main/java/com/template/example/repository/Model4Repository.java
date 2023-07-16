package com.template.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.example.models.Model4;

public interface Model4Repository extends JpaRepository<Model4, Long> {
    List<Model4> findByCol2(String col2); 
    // checking if foreign key exists
    // List<Model4> findByMId(Long mid); // doesn't work
}
