package com.example.nathanielneedham_comp303_assignment4.Repository;

import com.example.nathanielneedham_comp303_assignment4.Entity.BloodStock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BloodStockRepository extends MongoRepository<BloodStock, String> {

    // Custom Query to find BloodStock by blood group
    List<BloodStock> findByBloodGroup(String bloodGroup);


}

