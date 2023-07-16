package com.template.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "model")
public class Models {

    // Generating the Primary Key

    @Id // specifying this is a primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)// telling db to auto increment
    private long mid; // providing PK value.

    // Column value.
    @Column(name = "col1", nullable=false)
    private String col1;

    // Default Constructor

    public Models(){

    }

    // Constructors

    public Models(String col1) {
        this.col1 = col1;
    }

    // Getters and Setters

    public long getMid() {
        return mid;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }
    

}
