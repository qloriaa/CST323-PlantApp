package com.cst323activity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cst323activity.model.PlantModel;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/plants")
public class PlantController {

	//@Autowired 
	//private OrdersBusinessServiceInterface service;
	
	//@Autowired 
	//private ProductsBusinessService productService;

	/**
	 * "/plants/" - plants directory
	 * 		Reached by selecting the "Plants" option in the navigation bar menu.
	 * 
	 * @param model
	 * @return "list" view
	 */
	@GetMapping("/")
	public String display(Model model) {
		model.addAttribute("title", "View All Plants");
		//model.addAttribute("plantModel", new PlantModel());

		return "list";
	}
	/**
	 * "/plants/new" - Display form to add new Plant Entry to dictionary.
	 *  
	 * @param model
	 * @return "addPlant" view
	 */
	@GetMapping("new")
	public String newPlant (Model model) {
        model.addAttribute("title", "New Plant Entry");
			
		return "addPlant";
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