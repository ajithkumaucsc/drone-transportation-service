package com.drone.transportation.dto;

import java.util.List;

public class DroneMedicationResponseDTO {

    private Long id;
    private List<MedicationDto> medications;
    private Double total_weight;

    public DroneMedicationResponseDTO(){
    	
    }
    public DroneMedicationResponseDTO(Long id, List<MedicationDto> medications, Double total_weight) {
        this.id = id;
        this.medications = medications;
        this.total_weight = total_weight;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MedicationDto> getMedications() {
		return medications;
	}

	public void setMedications(List<MedicationDto> medications) {
		this.medications = medications;
	}

	public Double getTotal_weight() {
		return total_weight;
	}

	public void setTotal_weight(Double total_weight) {
		this.total_weight = total_weight;
	}

   
}
