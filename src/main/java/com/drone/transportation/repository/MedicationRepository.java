package com.drone.transportation.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drone.transportation.entity.DroneEntity;
import com.drone.transportation.entity.MedicationEntity;


public interface MedicationRepository extends JpaRepository<MedicationEntity, Long>{
	Optional<MedicationEntity> findById(int id);


}
