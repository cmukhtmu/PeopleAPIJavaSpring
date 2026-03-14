package com.example.controller;

import com.example.model.AddressHistory;
import com.example.service.AddressHistoryService;
import com.example.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressHistory")
@CrossOrigin(origins = "*")
public class AddressHistoryController {
    
    @Autowired
    private AddressHistoryService addressHistoryService;
    @Autowired
    private PersonService personService;
    
    // GET all addressHistory
    @GetMapping
    public ResponseEntity<List<AddressHistory>> getAllAddressHistory() {
        List<AddressHistory> addressHistory = addressHistoryService.getAllAddressHistory();
        return ResponseEntity.ok(addressHistory);
    }
    
    // GET addressHistory by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressHistory> getAddressHistoryById(@PathVariable Long id) {
        Optional<AddressHistory> addressHistory = addressHistoryService.getAddressHistoryById(id);
        return addressHistory.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // POST create new addressHistory
    @PostMapping
    public ResponseEntity<AddressHistory> createAddressHistory(@RequestBody AddressHistory addressHistory) {
        AddressHistory savedAddressHistory = addressHistoryService.saveAddressHistory(addressHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddressHistory);
    }
    
    // PUT update addressHistory
    @PutMapping("/{id}")
    public ResponseEntity<AddressHistory> updateAddressHistory(@PathVariable Long id, @RequestBody AddressHistory addressHistory) {
        Optional<AddressHistory> existingAddressHistory = addressHistoryService.getAddressHistoryById(id);
        if (existingAddressHistory.isPresent()) {
            addressHistory.setId(id);
            AddressHistory updatedAddressHistory = addressHistoryService.saveAddressHistory(addressHistory);
            return ResponseEntity.ok(updatedAddressHistory);
        }
        return ResponseEntity.notFound().build();
    }
    
    // DELETE addressHistory by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddressHistory(@PathVariable Long id) {
        if (addressHistoryService.getAddressHistoryById(id).isPresent()) {
            addressHistoryService.deleteAddressHistoryById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/generate/{count}")
public ResponseEntity<String> generateRandomAddressHistory(@PathVariable int count) {

    // Get all people IDs
    List<Long> peopleIds = personService.getAllPersonIds();

    if (peopleIds.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No people found in the database.");
    }

    // Generate addresses
    List<AddressHistory> created = addressHistoryService.generateRandomAddresses(count, peopleIds);

    return ResponseEntity.ok("Generated " + created.size() + " address history records.");
}

}