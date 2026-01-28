package com.example.controller;

import com.example.model.Health;
import com.example.model.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/people")
@CrossOrigin(origins = "*")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    // GET all people
    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        List<Person> people = personService.getAllPeople();
        return ResponseEntity.ok(people);
    }
    
    // GET API health - MUST come before /{id} to avoid path collision
    @GetMapping("/health")
    public ResponseEntity<Health> getHealth() {
        boolean dbConnected = false;
        Health health = new Health();

        try {
            // Try to get the first person - simpler than looping
            Optional<Person> person = personService.getPersonById(1L);
            dbConnected = true;
        } catch (Exception e) {
            dbConnected = false;
            health.status = "ERROR";
        }
        health.database = dbConnected ? "Connected" : "Not Connected";
        return ResponseEntity.ok(health);
    }
    
    // GET person by ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.getPersonById(id);
        return person.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // POST create new person
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }
    
    // PUT update person
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Optional<Person> existingPerson = personService.getPersonById(id);
        if (existingPerson.isPresent()) {
            person.setId(id);
            Person updatedPerson = personService.savePerson(person);
            return ResponseEntity.ok(updatedPerson);
        }
        return ResponseEntity.notFound().build();
    }
    
    // DELETE person by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (personService.getPersonById(id).isPresent()) {
            personService.deletePersonById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}