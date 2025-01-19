package com.cst323activity.controller;

import com.cst323activity.model.PlantModel;
import com.cst323activity.service.PlantService;

import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/plants")
public class PlantController {

	// Implements CRUD operations
	@Autowired
	private PlantService plantService;

	/**
	 * "/plants/" - plants directory
	 * 		Reached by selecting the "View All" option in the navigation bar menu.
	 * 
	 * @param model is used to set page layout attributes.
	 * @return "plants" view to list plants.
	 */
	@GetMapping(value= {"", "/"})
	public String getAll (Model model) {

		model.addAttribute("title", "View All Plants");
		model.addAttribute("plants", plantService.getAll());

		return "plants";
	}

	/***
	 * "/plants/view/{id}" -View details for one plant entry
	 * 		Reached by selecting "View" button from "View All" page.
	 * 
	 * @param id Plant unique identifier
	 * @param model is used to set page layout attributes.
	 * @return "viewPlant" view to display all details, and update & delete options.
	 */
	@GetMapping("view/{id}")
	public String viewPlantDetails (@PathVariable("id") Long id, Model model) {
		// Get instance
		PlantModel plant = plantService.getPlant(id);

		// Not Found
		if (plant == null) {
			JOptionPane.showMessageDialog(null, "Entry not found.");
			return"redirect:/plants";
		}

		// Found
		model.addAttribute("title", "Plant Details");
		model.addAttribute("plant", plant);
			
		return "viewPlant";
	}


	/***
	 * "/plants/update/{id}" - Update form for selected Plant Entry.
	 * 
	 * @param id is unique identifier
	 * @param model is used to set page layout attributes.
	 * @return "updatePlant" form for user input.
	 */
	@GetMapping("update/{id}")
	public String updatePlant (@PathVariable("id") Long id, Model model) {
		PlantModel plant = plantService.getPlant(id);

		// Not found
		if (plant == null ){
			JOptionPane.showMessageDialog(null, "Entry not found.");
			return "redirect:/plants";
		}

		// Found
		model.addAttribute("title", "Update Plant Entry");
		model.addAttribute("plant", plant);

		return "updatePlant";
	}
	
	/***
	 * "/plants/update/{id}" - Validate Update form.
	 * 
	 * @param plant updated Plant instance based on user input. 
	 * @param bindingResult is used for validation.
	 * @param model is used to set page layout attributes.
	 * @return "viewPlant" to view updates if successful, else, "updatePlant" prompting user to fix input errors.
	 */
	@PostMapping("update")
	public String updatePlant (@Valid PlantModel plant, BindingResult bindingResult, Model model) {
		// Check for Errors
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("title", "Update Plant Entry");
			model.addAttribute("plant", plant);

			// Errors found, reload page for user input
			return ("updatePlant" + plant.getId().toString());
		}

		// No Errors found
		plantService.updatePlant(plant);
		return "redirect:/plants";
		//return "redirect:/view/" + plant.getId().toString();		
	}


	/***
	 * "/plants/delete/{id}" - Delete selected plant entry.
	 * 
	 * @param id is unique identifier.
	 * @param model is used to set plage layout attributes.
	 * @return "plants" redirects to view all plant entries if successful, else, reload plant entry.
	 */
	@GetMapping ("delete/{id}")
	public String deletePlant (@PathVariable("id") Long id, Model model) {

		PlantModel plant = plantService.getPlant(id);

		// If Plant not Found
		if (plant == null) {
			JOptionPane.showMessageDialog(null, "Error deleting entry.");
			return "redirect:/viewPlant/" + id.toString();
		}

		plantService.deletePlant(plant);
		return "redirect:/plants";
	}

	
	/***
	 * "/plants/input" - Display form to add new Plant Entry to dictionary.
	 *  
	 * @param model is used to set page layout attributes.
	 * @return "addPlant" view
	 */
	@GetMapping("input")
	public String createPlant (Model model) {
        model.addAttribute("title", "New Plant Entry");
		model.addAttribute("plantModel", new PlantModel());
			
		return "addPlant";
	}

	/***
	 * "/plants/input/" - Create and Add new Plant entry to database after validating user input.
	 * 
	 * @param plant instance of Plant based on user input.
	 * @param bindingResult is used to validate user input.
	 * @param model is used to set page layout attributes.
	 * @return "viewPlant" to display entry created, else, "addPlant" to prompt user to fix errors. 
	 */
	 @PostMapping("input")
	 public String createPlant(@Valid PlantModel plant, BindingResult bindingResult, Model model) {
	 	
		// Invalid user input
		if (bindingResult.hasErrors()) {
			model.addAttribute("plantModel", plant);

			return "addPlant";
		}

		// Entry created successfully
		plantService.savePlant(plant);
		
		return "redirect:/plants";
	 	//return "redirect:/viewPlant/" + plant.getId().toString();
	 }
	 


    /**
	 * "/plants/search" - Display form to search for a specific Plant Entry in dictionary.
	 *  
	 * @param model
	 * @return "search" view
	 */
	@GetMapping("search")
	public String search (Model model) {
        model.addAttribute("title", "Search for Plant");
			
		return "search";
	}
}