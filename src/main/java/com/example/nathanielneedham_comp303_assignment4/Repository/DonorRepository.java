package com.example.nathanielneedham_comp303_assignment4.Repository;

import com.example.nathanielneedham_comp303_assignment4.Entity.Donor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DonorRepository extends MongoRepository<Donor, String> {
    List<Donor> findByBloodGroup(String bloodGroup);
    List<Donor> findByFirstNameAndLastName(String firstName, String lastName);
}
