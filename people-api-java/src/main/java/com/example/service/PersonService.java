package com.example.service;

import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;
    
    // Get all people ordered by ID descending
    public List<Person> getAllPeople() {
        return personRepository.findAll().stream()
            .sorted((a, b) -> b.getId().compareTo(a.getId()))
            .toList();
    }
    
    // Get person by ID
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }
    
    // Create or update person
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
    
    // Delete person by ID
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
}