package com.template.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.example.models.Model3;

public interface Model3Repository extends JpaRepository<Model3, Long> {
    
    List<Model3> findTagsByModelsMid(Long ModelsId); 
}
