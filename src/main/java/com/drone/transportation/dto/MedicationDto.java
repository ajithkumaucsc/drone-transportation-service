package com.drone.transportation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class MedicationDto {
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9_-]+",message = "allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;
    
    @NotNull
    @Positive(message = "Invalid Weight")
    private double weight;
    
    @NotBlank
    @Pattern(regexp = "[A-Z_0-9]+",message = "allowed only upper case letters, underscore and numbers")
    private String code;
    
    private String image;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
    
}