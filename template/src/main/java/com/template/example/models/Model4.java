package com.template.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "model4")
public class Model4 {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long m4id;

    @Column(name = "col2", nullable=false)
    private String col2;

    // Showing relationships
    // in this example the foreign key is placed at the 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mid", referencedColumnName = "mid")
    private Models models; // Variable to store models data

    // Default constructor
    public Model4() {

    }
    // Constructor

    public Model4(String col2) {
        this.col2 = col2;
    }

    // Getters and Setters
    public long getM4id() {
        return m4id;
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
