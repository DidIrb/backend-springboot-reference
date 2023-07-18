package com.template.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.example.models.Model2;

import jakarta.transaction.Transactional;

public interface Model2Repository extends JpaRepository<Model2, Long> {
    // custom methods
    List<Model2> findByModelsMid(Long mid); 

    // List<Model2> deleteByModelsMid(Long m2id); 
    @Transactional
    void deleteByModelsMid(long ModelsMid);

}
