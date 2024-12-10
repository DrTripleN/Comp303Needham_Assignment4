package com.example.nathanielneedham_comp303_assignment4.Controller;


import com.example.nathanielneedham_comp303_assignment4.Entity.BloodBank;
import com.example.nathanielneedham_comp303_assignment4.Repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bloodbanks")
@CrossOrigin(origins = "http://localhost:3000")
public class BloodBankController {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    // Add a new Blood Bank
    @PostMapping("/add")
    public ResponseEntity<BloodBank> addBloodBank(@RequestBody BloodBank bloodBank) {
        BloodBank savedBloodBank = bloodBankRepository.save(bloodBank);
        return ResponseEntity.ok(savedBloodBank);
    }

    // Get all Blood Banks
    @GetMapping("/all")
    public ResponseEntity<List<BloodBank>> getAllBloodBanks() {
        List<BloodBank> bloodBanks = bloodBankRepository.findAll();
        return ResponseEntity.ok(bloodBanks);
    }

    // Get a Blood Bank by ID
    @GetMapping("/{id}")
    public ResponseEntity<BloodBank> getBloodBankById(@PathVariable String id) {
        Optional<BloodBank> bloodBank = bloodBankRepository.findById(id);
        return bloodBank.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Edit/Update a Blood Bank
    @PutMapping("/edit/{id}")
    public ResponseEntity<BloodBank> editBloodBank(@PathVariable String id, @RequestBody BloodBank updatedBloodBank) {
        return bloodBankRepository.findById(id).map(existingBloodBank -> {
            existingBloodBank.setBloodbankName(updatedBloodBank.getBloodbankName());
            existingBloodBank.setAddress(updatedBloodBank.getAddress());
            existingBloodBank.setCity(updatedBloodBank.getCity());
            existingBloodBank.setPhone(updatedBloodBank.getPhone());
            existingBloodBank.setWebsite(updatedBloodBank.getWebsite());
            existingBloodBank.setEmail(updatedBloodBank.getEmail());
            BloodBank savedBloodBank = bloodBankRepository.save(existingBloodBank);
            return ResponseEntity.ok(savedBloodBank);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a Blood Bank
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBloodBank(@PathVariable String id) {
        bloodBankRepository.deleteById(id);
        return ResponseEntity.ok("Blood Bank deleted successfully.");
    }
}