package com.example.nathanielneedham_comp303_assignment4.Controller;

import com.example.nathanielneedham_comp303_assignment4.Entity.BloodStock;
import com.example.nathanielneedham_comp303_assignment4.Repository.BloodStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bloodstocks")
@CrossOrigin(origins = "http://localhost:3000")
public class BloodStockController {

    @Autowired
    private BloodStockRepository bloodStockRepository;

    // Add new Blood Stock
    @PostMapping("/add")
    public ResponseEntity<BloodStock> addBloodStock(@RequestBody BloodStock bloodStock) {
        BloodStock savedBloodStock = bloodStockRepository.save(bloodStock);
        return ResponseEntity.ok(savedBloodStock);
    }

    // Get all Blood Stocks
    @GetMapping("/all")
    public ResponseEntity<List<BloodStock>> getAllBloodStocks() {
        List<BloodStock> bloodStocks = bloodStockRepository.findAll();
        return ResponseEntity.ok(bloodStocks);
    }

    // Get Blood Stock by ID
    @GetMapping("/{id}")
    public ResponseEntity<BloodStock> getBloodStockById(@PathVariable String id) {
        Optional<BloodStock> bloodStock = bloodStockRepository.findById(id);
        return bloodStock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Check Blood Availability by Blood Group
    @GetMapping("/availability/{bloodGroup}")
    public ResponseEntity<List<BloodStock>> checkBloodAvailability(@PathVariable String bloodGroup) {
        List<BloodStock> availableStock = bloodStockRepository.findByBloodGroup(bloodGroup);
        if (availableStock.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(availableStock);
    }

    // Update/Edit Blood Stock
    @PutMapping("/edit/{id}")
    public ResponseEntity<BloodStock> editBloodStock(@PathVariable String id, @RequestBody BloodStock updatedBloodStock) {
        return bloodStockRepository.findById(id).map(existingBloodStock -> {
            existingBloodStock.setBloodGroup(updatedBloodStock.getBloodGroup());
            existingBloodStock.setQuantity(updatedBloodStock.getQuantity());
            existingBloodStock.setBestBefore(updatedBloodStock.getBestBefore());
            existingBloodStock.setStatus(updatedBloodStock.getStatus());
            BloodStock savedBloodStock = bloodStockRepository.save(existingBloodStock);
            return ResponseEntity.ok(savedBloodStock);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Blood Stock
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBloodStock(@PathVariable String id) {
        bloodStockRepository.deleteById(id);
        return ResponseEntity.ok("Blood Stock deleted successfully.");
    }
}