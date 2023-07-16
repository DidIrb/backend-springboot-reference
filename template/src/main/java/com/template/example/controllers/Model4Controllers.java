package com.template.example.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.template.example.exceptions.ResourceNotFoundException;
import com.template.example.models.Model4;
import com.template.example.models.Models;
import com.template.example.repository.Model4Repository;
import com.template.example.repository.ModelsRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class Model4Controllers {

    @Autowired
    private Model4Repository model4Repository;

    @Autowired
    private ModelsRepository modelsRepository;

    // GET REQUEST

    // MODEL4 Values related to model
    @GetMapping({ "/model4/{mid}" })
    public ResponseEntity<Model4> getDetailsById(@PathVariable(value = "mid") Long mid) {
        Model4 model4Data = model4Repository.findById(mid)
                .orElseThrow(() -> new ResourceNotFoundException("Item with id : " + mid + " Was not found"));

        return new ResponseEntity<>(model4Data, HttpStatus.OK);
    }

    // CREATE
    @PostMapping("/model4/{mid}")
    public ResponseEntity<Model4> createDetails(@PathVariable(value = "mid") Long mid,
            @RequestBody Model4 model4Request) {
        // Check if model table column exists
        // List<Model4> model4s = model4Repository.findByMId(mid);

        // if (model4s.isEmpty()) {
        // // check if the item exists in our models table
        // Models models = modelsRepository.findById(mid)
        // .orElseThrow(() -> new ResourceNotFoundException("Item with id: = " + mid + "
        // Was not found"));
        // // Set the data
        // model4Request.setModels(models);
        // // Persist it in our DB
        // Model4 _model4 = model4Repository.save(model4Request);
        // return new ResponseEntity<>(_model4, HttpStatus.CREATED);
        // } else {
        // new ResourceNotFoundException("Item with id: " + mid + " Already belongs to
        // another model!!!!");
        // }
        Models models = modelsRepository.findById(mid)
                .orElseThrow(() -> new ResourceNotFoundException("Item with id:  = " + mid + " Was not found"));
        model4Request.setModels(models);
        // Persist it in our DB
        Model4 _model4 = model4Repository.save(model4Request);

        return new ResponseEntity<>(_model4, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/model4/{m4id}")
    public ResponseEntity<Model4> updateDetails(@PathVariable("m4id") long m4id,
        @RequestBody Model4 model4Request) {
        Model4 model4 = model4Repository.findById(m4id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + m4id + " not found"));

        model4.setCol2(model4Request.getCol2());

        return new ResponseEntity<>(model4Repository.save(model4), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/model4/{m4id}")
    public ResponseEntity<HttpStatus> deleteDetails(@PathVariable("m4id") long m4id) {
        model4Repository.deleteById(m4id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
