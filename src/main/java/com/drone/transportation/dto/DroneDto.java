package com.drone.transportation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.drone.transportation.utill.Model;
import com.drone.transportation.utill.State;

public class DroneDto {
	
    @Size(max = 100, message = "Serial number cannot exceed {max} characters")
    @NotEmpty(message = "serialNumber shouldn't be null")
    private String serialNumber;
    @NotNull(message = "Model shouldn't be null")
    @EnumValue(enumClass = Model.class, message = "Invalid drone model")
    private Model model;
    @Range(min = 50, max = 500, message = "WeightLimit must be between 50 and 500")
    private int weightLimit;
    @Range(min = 0, max = 100, message = "Battery capacity must be between 0 and 100")
    private int batteryCapacity;
    @NotNull
    @EnumValue(enumClass = State.class, message = "Invalid drone state")
    private State state;
    public DroneDto() {
    }
    public DroneDto(String serialNumber, Model model, int weightLimit, int batteryCapacity, State state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
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
}
