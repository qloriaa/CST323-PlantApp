package com.cst323activity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.cst323activity.model.PlantModel;

/***
 * Business service responsible for all CRUD operations
 */
@Service
public class PlantService {

    // Repository to access database (via direct injection)    
    @Autowired
    private PlantRepository plantRepository;

    /***
     * Create - create a new plant obj
     * 
     * @param plant Instance of Plant received to save to database
     * @return Plant ID if successful, else, -1 if could not be saved;
     */
    public long savePlant (PlantModel plant) {
        try {
            // add plant
            plantRepository.save(plant);

            System.out.println("Plant Added: " + plant.getName());

            return plant.getId();
        } catch (Exception ex){
            ex.printStackTrace();

            return -1;
        }

    }

    /***
     * Read - Get All the Plants in Database
     * 
     * @return List of all Plants in database
     */
    public List<PlantModel> getAll () {
        try{
            List<PlantModel> plantsFound = plantRepository.findAll();

            return plantsFound;
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }

    /***
     * Read - Search for a Plant in database using ID. 
     * 
     * @param Id Plant's unique identifier in database. 
     * @return Plant instance, else, null if could not be found.
     */
    public PlantModel getPlant (Long Id) {
        
        Optional<PlantModel> plantResult = plantRepository.findById(Id);

        try {
            PlantModel plantFound = plantResult.get();

            return plantFound;
        } catch (Exception ex ) {
            ex.printStackTrace();

            return null;
        }
    }

    /***
     * Update - Update contents using JpaRepository save(T) which automatically updates object if it already exists.
     * 
     * @param plant Updated plant instance.
     * @return Plant unique identifier if successful, else, -1 if update failed.
     */
    public long updatePlant (PlantModel plant) {
        return savePlant(plant);
    }

    /***
     * Delete - Delete selected Plant from database.
     * 
     * @param plant Selected Plant
     * @return true if successful, else, false for any errors.
     */
    public boolean deletePlant (PlantModel plant) {

        try {
            plantRepository.delete(plant);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();;

            return false;
        }
    }

    /***
     * Read - Get all Plants matching search term.
     * 
     * @param searchTerm User input.
     * @return List of all Plants containing user input.
     */
    public List<PlantModel> search (String searchTerm) {

        try {
            List<PlantModel> plantResults = plantRepository.findByName(searchTerm);

            return plantResults;

        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }

    }

    
}
