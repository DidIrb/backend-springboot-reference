package com.starter.startercode.model;

// import org.hibernate.annotations.GenericGenerator; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// This will interact with define how a table in the DB will look like


@Entity // this indicates that it is a persistent java class
@Table(name = "tutorials") // Specifying the tables name.
public class Tutorial {
    
    @Id // Specifying that it is an Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // tells the db the column is of auto increment	
    private long id; // defining the type.

    @Column(name = "title") // Specifying that this is a column in the table
    private String title; 

    @Column(name = "description")
    private String description; 
    
    @Column(name = "published")
    private boolean published;

    
    // Below is a no arg constructor and it is necessary.
    public Tutorial() {

    }
    
    // Adding the constructor to build the object that is to be passed to the DB.
    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    } 


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	}
    

}