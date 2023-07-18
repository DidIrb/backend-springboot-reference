package com.template.example.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.template.example.repository.ModelsRepository;
import com.template.example.exceptions.ResourceNotFoundException;
import com.template.example.models.Models;

@CrossOrigin(origins = "http://localhost:5678")
@RestController
@RequestMapping("/api")
public class ModelControllers {

    @Autowired
    ModelsRepository modelRepository;

    // READ / GET REQUESTS
    @GetMapping("/model")
    public ResponseEntity<List<Models>> getAllModel(@RequestParam(required = false) String col1) {
        List<Models> ModelData = new ArrayList<Models>();

        if (col1 == null)
            modelRepository.findAll().forEach(ModelData::add);
        else
            modelRepository.findByCol1(col1).forEach(ModelData::add);

        // checking if the table is empty and returning message
        if (ModelData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ModelData, HttpStatus.OK);

    }

    @GetMapping("/model/{mid}")
    public ResponseEntity<Models> getModelsById(@PathVariable("mid") long mid) {
        
        Models _model = modelRepository.findById(mid)
                .orElseThrow(() -> new ResourceNotFoundException("The item with the id : " + mid + " Was not found!!"));

        return new ResponseEntity<>(_model, HttpStatus.OK);

    }

    // CREATE / POST REQUESTS
    @PostMapping("/model")
    public ResponseEntity<Models> addUser(@RequestBody Models modelRequest) {
        Models models = modelRepository.save(modelRequest);
        // use a custom success message
        return new ResponseEntity<>(models, HttpStatus.CREATED);
    }

    // UPDATE / PUT OR PATCH REQUESTS
    @PutMapping("/model/{mid}")
    public ResponseEntity<Models> updateUser(@PathVariable("mid") long mid, @RequestBody Models modelRequest) {
        // check if model column with specified id exists

        Models _model = modelRepository.findById(mid)
                .orElseThrow(() -> new ResourceNotFoundException("The item with the id : " + mid + " Was not found!!"));
        // Setting the data in the object
        _model.setCol1(modelRequest.getCol1());

        // Research how to throw custom success message
        return new ResponseEntity<>(modelRepository.save(_model), HttpStatus.OK);
        // return "successfully update the model with with "; // this wont work
    }

    // DELETE / DELETE REQUEST
    @DeleteMapping("/model/{mid}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("mid") long mid) {
        // check if it exists
        if (modelRepository.existsById(mid)) {
            modelRepository.deleteById(mid);
        }
        // Return a response message eg deleted successfully
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
