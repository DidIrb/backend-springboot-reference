package com.starter.startercode.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starter.startercode.model.Tutorial;
import com.starter.startercode.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8080") // specifying the port etc.
@RestController
@RequestMapping("/api") // specifying that we are handling requests
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository; // importing the TutorialRepository then renaming storing it in tutorialRepository;

    // GET REQUEST
    @GetMapping("/tutorials") // GetMapping tells us we are handling a get Request, ("endpoint")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        try { // if successful the below code snippet is to be used.
            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) { // handling error
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET BY ID REQUEST getting one record.
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
        return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Custom method to check if the tutorial was published'
    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        try {
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST REQUEST
    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
        Tutorial _tutorial = tutorialRepository
            .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT Request Method to update data checking the id.
    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
        Tutorial _tutorial = tutorialData.get();
        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());
        return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Delete Request deleting an item using the id.
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
        tutorialRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Deleting All
    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
        tutorialRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    


}
