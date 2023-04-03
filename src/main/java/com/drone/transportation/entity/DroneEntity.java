package com.drone.transportation.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.drone.transportation.utill.Model;
import com.drone.transportation.utill.State;

@Entity
@Table(name="drone")
public class DroneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "serial_number", length = 100)
    private String serialNumber;
    
    @Enumerated(EnumType.STRING)
    private Model model;
    
    @Column(name = "weight_limit")
    private int weightLimit;
    
    @Column(name = "battery_capacity")
    private int batteryCapacity;
    
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drone")
    private Set<MedicationEntity> medications;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(int weightLimit) {
		this.weightLimit = weightLimit;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Set<MedicationEntity> getMedications() {
		return medications;
	}

	public void setMedications(Set<MedicationEntity> medications) {
		this.medications = medications;
	}
    
   
}
