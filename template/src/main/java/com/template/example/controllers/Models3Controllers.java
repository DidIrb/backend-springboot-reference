package com.template.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.example.exceptions.ResourceNotFoundException;
import com.template.example.models.Model3;
import com.template.example.models.Models;
import com.template.example.repository.Model3Repository;
import com.template.example.repository.ModelsRepository;

@CrossOrigin(origins = "http://localhost:5678")
@RestController
@RequestMapping("/api")
public class Models3Controllers {

    @Autowired
    ModelsRepository modelsRepository;

    @Autowired
    Model3Repository model3Repository;

    // Getting all record in table model3
    @GetMapping("/model3")
    public ResponseEntity<List<Model3>> getAllModel3s() {
        List<Model3> model3s = new ArrayList<Model3>();

        model3Repository.findAll().forEach(model3s::add);

        if (model3s.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(model3s, HttpStatus.OK);
    }

    // Creating a user
    @PostMapping("/model3")
    public ResponseEntity<Model3> addUser(@RequestBody Model3 model3Request) {
        Model3 model3 = model3Repository.save(model3Request);
        return new ResponseEntity<>(model3, HttpStatus.CREATED);
    }

    //

    // More like assigning a document to a user than creating a user This method
    // acts both as the create and update method.

    @PostMapping("/model3s/{mid}")
    public ResponseEntity<Models> addModels(@PathVariable(value = "mid") Long mid,
            @RequestBody Models Request) {
        // going to our model3s table and checking if the value doc_id passed in the
        // params exists
        Models models = model3Repository.findById(mid).map(modelsData -> {
            long m_id = Request.getMid(); // passing users id into variable m_id

            // Checking If item Exists
            if (m_id != 0L) {
                Models _model3 = modelsRepository.findById(m_id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Item with id = " + m_id + "Was not found!!!"));
                // update the user
                modelsRepository.save(Request);
                // persist the data in the db.
                modelsData.addModels(_model3);
                // saving the document foreign key in our join table
                model3Repository.save(modelsData);
                return _model3;
            }

            // // Add create new user
            modelsData.addModels(Request);
            return modelsRepository.save(Request);

        }).orElseThrow(() -> new ResourceNotFoundException("Document with id = " + mid + "was not found!!!"));

        return new ResponseEntity<>(models, HttpStatus.CREATED);
    }

}
