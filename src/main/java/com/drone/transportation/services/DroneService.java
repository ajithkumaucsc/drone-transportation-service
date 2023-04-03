package com.drone.transportation.services;


import java.util.List;

import com.drone.transportation.dto.DroneDto;
import com.drone.transportation.dto.DroneMedicationResponseDTO;
import com.drone.transportation.dto.MedicationDto;
import com.drone.transportation.entity.DroneEntity;
import com.drone.transportation.exception.DroneStateException;


public interface DroneService {

	DroneEntity droneRegistration(DroneDto droneDto)throws DroneStateException;

	DroneDto getDroneById(Long droneId) throws DroneStateException;

	boolean addMedicationsToDrone(Long droneId, List<MedicationDto> medications) throws DroneStateException;

	DroneMedicationResponseDTO getMedicationByDroneId(Long droneId)throws DroneStateException;

	List<DroneEntity> getAllDrones() throws DroneStateException;

	void logEvent(DroneEntity drone, String string);

	List<DroneEntity> getAvilableDrones()throws DroneStateException;

}
