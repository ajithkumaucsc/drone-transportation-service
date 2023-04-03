package com.drone.transportation.repository;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.drone.transportation.entity.DroneEntity;


public interface DroneRepository extends JpaRepository<DroneEntity, Long>{
	Optional<DroneEntity> findById(int id);
	@Query(value = "SELECT de FROM DroneEntity de WHERE de.state ='IDLE' AND de.batteryCapacity > 25 ")
	ArrayList<DroneEntity> findByStateAndBatteryCapacity();
	Optional<DroneEntity> findFirstBySerialNumber(String serialNumber);

}
