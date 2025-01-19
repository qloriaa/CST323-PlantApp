package com.cst323activity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cst323activity.model.PlantModel;

public interface PlantRepository extends JpaRepository<PlantModel, Long> {
   
    // used to select Plant 
    Optional findById(Long id);
    
    // used for search function
    List<PlantModel> findByName (String searchTerm);

    //List<PlantModel> findBySciNameContainingIgnoreCase (String searchTerm);

    //List<PlantModel> findByFamNameContainingIgnoreCase (String searchTerm);


    
}
