package com.template.example.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "model2")
public class Model2 {
    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long m2id;

    // Column value.
    @Column(name = "col2", nullable = false)
    private String col2;

    // joining the tables
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "models_mid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Models models;
    
    // Default Constructors
    public Model2() {
        
    }

    // Constructors

    public Model2(String col2, Models models) {
        this.col2 = col2;
        this.models = models;
    }

    public long getM2id() {
        return m2id;
    }

    public void setM2id(long m2id) {
        this.m2id = m2id;
    }

    // getters and setters
    public long getM2Id() {
        return m2id;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public Models getModels() {
        return models;
    }

    public void setModels(Models models) {
        this.models = models;
    }

}
