package com.drone.transportation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.transportation.dto.DroneDto;
import com.drone.transportation.dto.DroneMedicationResponseDTO;
import com.drone.transportation.dto.MedicationDto;
import com.drone.transportation.entity.DroneEntity;
import com.drone.transportation.exception.DroneStateException;
import com.drone.transportation.services.DroneService;

@RestController
@RequestMapping("/api/v1")
public class DroneController {
	@Autowired
	private DroneService droneService;

	@PostMapping("/droneRegistration")
	public ResponseEntity<DroneEntity> posInputRegistration(@RequestBody @Valid DroneDto droneDto)
			throws DroneStateException {
		return new ResponseEntity<>(droneService.droneRegistration(droneDto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DroneDto> getDroneById(@PathVariable Long droneId) throws DroneStateException {
		return ResponseEntity.ok(droneService.getDroneById(droneId));
	}

	@PostMapping("/loadMedicationByDroneID/{droneId}")
	public ResponseEntity<?> addMedicationsToDrone(@PathVariable("droneId") Long droneId,
			@RequestBody @Valid List<MedicationDto> medications) throws DroneStateException {
		if (medications == null || medications.isEmpty()) {
			return ResponseEntity.badRequest().body("List of medications cannot be empty");
		}
		boolean result = droneService.addMedicationsToDrone(droneId, medications);
		if (result) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/getMedicationByDroneId/{drone_id}")
	public DroneMedicationResponseDTO getMedicationByDroneId(@PathVariable("drone_id") Long droneId)
			throws DroneStateException {
		DroneMedicationResponseDTO droneMedicationResponseDTO = droneService.getMedicationByDroneId(droneId);
		return droneMedicationResponseDTO;

	}

	@GetMapping("/getAvilableDrones")
	public List<DroneEntity> getAvilableDrones() throws DroneStateException {
		List<DroneEntity> avilableDroneList = droneService.getAvilableDrones();
		return avilableDroneList;

	}

	@GetMapping("/checkBatteryLevelByDroneId/{drone_id}")
	public ResponseEntity<DroneDto> checkBatteryLevelByDroneId(@PathVariable("drone_id") Long droneId)
			throws DroneStateException {
		return ResponseEntity.ok(droneService.getDroneById(droneId));
	}
}