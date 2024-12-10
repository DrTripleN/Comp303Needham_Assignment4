package com.example.nathanielneedham_comp303_assignment4.Repository;

import com.example.nathanielneedham_comp303_assignment4.Entity.BloodBank;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BloodBankRepository extends MongoRepository<BloodBank, String> {

    // Custom Query to find a Blood Bank by name
    List<BloodBank> findByBloodbankName(String bloodbankName);

    // Custom Query to find Blood Banks in a specific city
    List<BloodBank> findByCity(String city);

    // Custom Query to find a Blood Bank by phone number
    List<BloodBank> findByPhone(String phone);

}