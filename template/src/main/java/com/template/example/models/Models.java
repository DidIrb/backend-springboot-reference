package com.template.example.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class Models {

    // Generating the Primary Key

    @Id // specifying this is a primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // telling db to auto increment
    private long mid; // providing PK value.

    // Column value.
    @Column(name = "col1", nullable = false)
    private String col1;

    // joining the tables
    @OneToOne(mappedBy = "models") // table it is connected to
    private Model4 model4;

    // making connection to the join table
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "model3s_models", joinColumns = {
            @JoinColumn(name = "models_id") }, 
            inverseJoinColumns = {
                    @JoinColumn(name = "model3_id") })
    private Set<Model3> model3s = new HashSet<>();

    // Default Constructor

    public Models() {

    }

    // Constructors

    public Model4 getModel4() {
        return model4;
    }

    public void setModel4(Model4 model4) {
        this.model4 = model4;
    }

    public Set<Model3> getModel3s() {
        return model3s;
    }

    public void setModel3s(Set<Model3> model3s) {
        this.model3s = model3s;
    }

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

    public void setMid(long mid) {
        this.mid = mid;
    }

    

}
