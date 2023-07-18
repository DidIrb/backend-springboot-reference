package com.template.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.example.exceptions.ResourceNotFoundException;
import com.template.example.models.Model2;
import com.template.example.repository.Model2Repository;
import com.template.example.repository.ModelsRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class Model2Controllers {
    @Autowired
    Model2Repository model2Repository;

    @Autowired
    ModelsRepository modelsRepository;

    // get by foreign key
    @GetMapping(value = "/model2/{mid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Model2>> getModels2ByFKId(@PathVariable(value = "mid") Long mid) {
        List<Model2> model2Data = new ArrayList<Model2>();
        // Getting by FK
        model2Repository.findByModelsMid(mid).forEach(model2Data::add);
        // .orElseThrow(() -> new ResourceNotFoundException("Item with id : " + m2id + "
        // Was not found"));
        if (model2Data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(model2Data);
    }

    // get by primary key

    // Create request
    // POST REQUEST
    @PostMapping("/model2/{mid}")
    public ResponseEntity<Model2> createModel2(@PathVariable(value = "mid") Long mid,
            @RequestBody Model2 model2Request) {

        Model2 model2 = modelsRepository.findById(mid).map(model -> {
            model2Request.setModels(model);
            return model2Repository.save(model2Request);
        }).orElseThrow(() -> new ResourceNotFoundException("Models data with id = " + mid + " was not found"));

        return new ResponseEntity<>(model2, HttpStatus.CREATED);
    }

    // PUT REQUEST
    @PutMapping("/model2/{id}")
    public ResponseEntity<Model2> updateModel2(
            @PathVariable("id") long id, @RequestBody Model2 model2Request) {
        Model2 model2 = model2Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Models with id " + id + " was not found"));

        model2.setCol2(model2Request.getCol2());

        return new ResponseEntity<>(model2Repository.save(model2), HttpStatus.OK);
    }

    // change relations
    @PatchMapping("/model2/{mid}")
    public ResponseEntity<Model2> updateModel2Relations(
            @PathVariable("mid") long mid, @RequestBody Model2 model2Request) {

        Long m2id = model2Request.getM2Id();

        Model2 model2 = modelsRepository.findById(mid).map(model -> {
                // Checking if the model we want exists

                Model2 _model2 = model2Repository.findById(m2id)
                        .orElseThrow(() -> new ResourceNotFoundException("Models 2 table column  with id " + m2id + " was not found"));

                // checking if the id exists     
                
                _model2.setCol2(model2Request.getCol2());
                _model2.setModels(model);
                return model2Repository.save(_model2);
            }).orElseThrow(() -> new ResourceNotFoundException("Models table column with id = " + mid + " was not found"));

        return new ResponseEntity<>(model2Repository.save(model2), HttpStatus.OK);
    }

    // DELETE REQUEST
    // Delete based on the foreign key
    @DeleteMapping("/model2/{mid}")
    public ResponseEntity<List<Model2>> deleteAllModel2sOfTutorial(
            @PathVariable(value = "mid") Long mid) {

        if (!modelsRepository.existsById(mid)) {
            throw new ResourceNotFoundException("Models table row with id = " + mid + "was not found");
        }

        model2Repository.deleteByModelsMid(mid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
