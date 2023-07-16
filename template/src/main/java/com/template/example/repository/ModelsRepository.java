package com.template.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.example.models.Models;

// Importing 
public interface ModelsRepository
extends JpaRepository<Models, Long> {
    // The method below will generate a list of type Models
    List<Models> findByCol1(String col1); 
    // it will make a request to the database 
    // checking if the column, col1 has a value passed in the controller
}

