package com.cst323activity.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Component
@Entity
@Table(name="plants")
public class PlantModel {
    // ---- ATTRIBUTES ----

    // Unique Identifier
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    @NotNull(message="Name is Required.")
    @Size(min=1, max=50, message="Name limit is 50 characters")
    private String name;

    @Column(name="scientific_name")
    @NotNull(message="Scientific Name is Required")
    @Size(min=1, max=50, message="Limit is 50 characters")
    private String sciName;

    @Column(name="family_name")
    @NotNull(message="Family Name is Required")
    @Size(min=1, max=50, message="Limit is 50 characters")
    private String famName;

    @Column(name="description")
    @NotNull(message="Brief description is required")
    private String description;

    //  ---- CONSTRUCTORS ----

    public PlantModel(Long id,
            @NotNull(message = "Name is Required.") @Size(min = 1, max = 50, message = "Name limit is 50 characters") String name,
            @NotNull(message = "Scientific Name is Required") @Size(min = 1, max = 50, message = "Limit is 50 characters") String sciName,
            @NotNull(message = "Family Name is Required") @Size(min = 1, max = 50, message = "Limit is 50 characters") String famName,
            @NotNull(message = "Brief description is required") String description) {
        id = this.id;
        name = this.name;
        sciName = this.sciName;
        famName = this.famName;
        description = this.description;
    }

    public PlantModel() {
    }

    public PlantModel (PlantModel plant) {
        id = plant.getId();
        name = plant.getName();
        sciName = plant.getSciName();
        famName = plant.getFamName();
        description = plant.getDescription();
    }

    // ---- MUTATORS AND ACCESSORS ----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = this.name;
    }

    public String getSciName() {
        return sciName;
    }

    public void setSciName(String sciName) {
        sciName = this.sciName;
    }

    public String getFamName() {
        return famName;
    }

    public void setFamName(String famName) {
        famName = this.famName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = this.description;
    }

        
}
