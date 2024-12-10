package com.example.nathanielneedham_comp303_assignment4.Controller;

import com.example.nathanielneedham_comp303_assignment4.Entity.Donor;
import com.example.nathanielneedham_comp303_assignment4.Repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from frontend
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    // Add a new donor
    @PostMapping("/add")
    public ResponseEntity<Donor> addDonor(@RequestBody Donor donor) {
        Donor savedDonor = donorRepository.save(donor);
        return ResponseEntity.ok(savedDonor);
    }

    // Get all donors
    @GetMapping("/all")
    public ResponseEntity<List<Donor>> getAllDonors() {
        List<Donor> donors = donorRepository.findAll();
        return ResponseEntity.ok(donors);
    }

    // Get a specific donor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable String id) {
        Optional<Donor> donor = donorRepository.findById(id);
        return donor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Edit/Update donor information
    @PutMapping("/edit/{id}")
    public ResponseEntity<Donor> editDonor(@PathVariable String id, @RequestBody Donor updatedDonor) {
        return donorRepository.findById(id).map(existingDonor -> {
            // Append to existing donation history instead of overwriting
            String newDonationHistory = updatedDonor.getDonationHistory();
            existingDonor.setFirstName(updatedDonor.getFirstName());
            existingDonor.setLastName(updatedDonor.getLastName());
            existingDonor.setDob(updatedDonor.getDob());
            existingDonor.setGender(updatedDonor.getGender());
            existingDonor.setBloodGroup(updatedDonor.getBloodGroup());
            existingDonor.setCity(updatedDonor.getCity());
            existingDonor.setPhone(updatedDonor.getPhone());

            // Append the new donation history if it exists
            if (newDonationHistory != null && !newDonationHistory.isEmpty()) {
                String existingHistory = existingDonor.getDonationHistory();
                existingDonor.setDonationHistory(
                        (existingHistory == null ? "" : existingHistory) + ";" + newDonationHistory
                );
            }
            Donor savedDonor = donorRepository.save(existingDonor);
            return ResponseEntity.ok(savedDonor);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a donor by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable String id) {
        return donorRepository.findById(id).map(donor -> {
            donorRepository.deleteById(id);
            return ResponseEntity.ok("Donor deleted successfully.");
        }).orElseGet(() -> ResponseEntity.status(404).body("Donor not found."));
    }

    // Login by first name and last name
    @PostMapping("/login")
    public ResponseEntity<Donor> loginDonor(@RequestParam String firstName, @RequestParam String lastName) {
        List<Donor> matchingDonors = donorRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!matchingDonors.isEmpty()) {
            Donor donor = matchingDonors.get(0);
            donor.setLoggedIn(true);
            donor.setLastLoginDate(new Date());
            donorRepository.save(donor);

            // Return the donor object (or a subset of its fields)
            return ResponseEntity.ok(donor); // This will return the entire Donor object
        }
        return ResponseEntity.status(401).body(null); // Return null for invalid credentials
    }
    // Logout the donor
    @PostMapping("/logout/{id}")
    public ResponseEntity<String> logoutDonor(@PathVariable String id) {
        return donorRepository.findById(id).map(donor -> {
            donor.setLoggedIn(false);
            donorRepository.save(donor);
            return ResponseEntity.ok("Logout successful!");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get logged-in donor's donation history
    @GetMapping("/history/{id}")
    public ResponseEntity<String> getDonationHistory(@PathVariable String id) {
        return donorRepository.findById(id).map(donor -> {
            if (donor.isLoggedIn()) {
                return ResponseEntity.ok(donor.getDonationHistory());
            }
            return ResponseEntity.status(401).body("User is not logged in.");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
