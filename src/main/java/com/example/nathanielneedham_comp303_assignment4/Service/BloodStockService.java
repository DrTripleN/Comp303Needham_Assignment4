package com.example.nathanielneedham_comp303_assignment4.Service;

import com.example.nathanielneedham_comp303_assignment4.Entity.BloodStock;
import com.example.nathanielneedham_comp303_assignment4.Repository.BloodStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BloodStockService {

    @Autowired
    private BloodStockRepository bloodStockRepository;

    public List<BloodStock> checkBloodAvailability(String bloodGroup) {
        return bloodStockRepository.findByBloodGroup(bloodGroup);
    }
}
