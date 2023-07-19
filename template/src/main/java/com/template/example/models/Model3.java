package com.template.example.models;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.example.enumTypes.Col3Types;

import jakarta.persistence.*;

@Entity
@Table(name = "model3s")
public class Model3 {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long m3id;

    @Column(name = "col2", nullable=false)
    private String col2;

    @Column(name = "col3", nullable=false)
    private Col3Types col3;

    // making connection to join table
    @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      mappedBy = "model3s")
    @JsonIgnore
    private Set<Models> models = new HashSet<>();


    public long getM3id() {
      return m3id;
    }
    // default constructor

    public Model3() {

    }

    // constructors 

    // getters and setters 

    public void setM3id(long m3id) {
      this.m3id = m3id;
    }

    public String getCol2() {
      return col2;
    }

    public void setCol2(String col2) {
      this.col2 = col2;
    }

    public Col3Types getCol3() {
      return col3;
    }

    public void setCol3(Col3Types col3) {
      this.col3 = col3;
    }

    // getting the models

    public Set<Models> getModels() {
      return models;
    }

    public void setModels(Set<Models> models) {
      this.models = models;
    }

    // adding models
    //
    public void addModels(Models model) {
      this.models.add(model);
      model.getModel3s().add(this);
}

}
