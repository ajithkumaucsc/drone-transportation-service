package com.drone.transportation.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.drone.transportation.dto.DroneDto;
import com.drone.transportation.dto.DroneMedicationResponseDTO;
import com.drone.transportation.dto.MedicationDto;
import com.drone.transportation.entity.DroneEntity;
import com.drone.transportation.entity.EventEntity;
import com.drone.transportation.entity.MedicationEntity;
import com.drone.transportation.exception.DroneStateException;
import com.drone.transportation.repository.DroneRepository;
import com.drone.transportation.repository.EventRepository;
import com.drone.transportation.repository.MedicationRepository;
import com.drone.transportation.utill.State;

@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	DroneRepository droneRepository;
	@Autowired
	MedicationRepository medicationRepository;
	@Autowired
	EventRepository eventRepository;

	@Override
	public DroneEntity droneRegistration(DroneDto droneDto) throws DroneStateException {
		DroneEntity droneEnt = droneDtoToEntity(droneDto);
		Optional<DroneEntity> checkDroneBySerialNumber = droneRepository.findFirstBySerialNumber(droneDto.getSerialNumber());
		if(checkDroneBySerialNumber.isPresent()){
			throw new DroneStateException("Drone already exist.");
		}
		return droneRepository.save(droneEnt);
	}

	@Override
	public DroneDto getDroneById(Long droneId) throws DroneStateException {
		Optional<DroneEntity> droneEnt = droneRepository.findById(droneId);
		if(!droneEnt.isPresent()){
			 throw new DroneStateException("The drone with the provided ID was not found. Please check the ID and try again");			
		}
		return droneEntityToDto(droneEnt.get());
	}
	
	public DroneDto droneEntityToDto(DroneEntity droneEnt) {
		DroneDto droneDto = new DroneDto(droneEnt.getSerialNumber(), droneEnt.getModel(), droneEnt.getWeightLimit(),
				droneEnt.getBatteryCapacity(), droneEnt.getState().IDLE);
		return droneDto;

	}

	public DroneEntity droneDtoToEntity(DroneDto droneDto) {
		DroneEntity droneEntity = new DroneEntity();
		droneEntity.setSerialNumber(droneDto.getSerialNumber());
		droneEntity.setModel(droneDto.getModel());
		droneEntity.setBatteryCapacity(droneDto.getBatteryCapacity());
		droneEntity.setWeightLimit(droneDto.getWeightLimit());
		droneEntity.setState(droneDto.getState());
		return droneEntity;
	}

	@Override
	public boolean addMedicationsToDrone(Long droneId, List<MedicationDto> medications) throws DroneStateException {
		    if(!validateMedicationList(medications)){
		    	throw new DroneStateException("Medication name or code invalid.");	
		    }
			Optional<DroneEntity> droneEnt = droneRepository.findById(droneId);
			if(!droneEnt.isPresent()){
				 throw new DroneStateException("The drone with the provided ID was not found. Please check the ID and try again");			
			}else if(droneEnt.get().getState() != State.IDLE){
				 throw new DroneStateException("Cannot load medication on drone while it is not in IDLE state. Current state is: "+droneEnt.get().getState());			
			}else if(droneEnt.get().getBatteryCapacity() < 25){
				 throw new DroneStateException("Battery level is too low to load the drone");			
			}
			
		  
           double sumofMedication=  medications.stream().filter(medication -> medication.getWeight() > 0).mapToDouble(medication -> medication.getWeight()).sum();

		    if (sumofMedication > droneEnt.get().getWeightLimit()) {
		    	throw new DroneStateException("Drone cannot carry this weight.");	
		    }
		    
		    for(MedicationDto meDto:medications){
		    	
		    	MedicationEntity medicationEnt=new MedicationEntity();
		    	medicationEnt.setCode(meDto.getCode());
		    	medicationEnt.setName(meDto.getName());
		    	medicationEnt.setDrone(droneEnt.get());
		    	medicationEnt.setImageUrl(meDto.getImage());
		    	medicationEnt.setWeight(meDto.getWeight());
		    	medicationRepository.save(medicationEnt);
		    
		    }
		    droneEnt.get().setState(State.LOADED);
		    droneRepository.save(droneEnt.get());
		   return true;
	}
	
	@Override
	public DroneMedicationResponseDTO getMedicationByDroneId(Long droneId) throws DroneStateException {
		Optional<DroneEntity> droneEnt = droneRepository.findById(droneId);
		
		if(!droneEnt.isPresent()){
			throw new DroneStateException("The drone with the provided ID was not found. Please check the ID and try again");			
		}
		DroneMedicationResponseDTO droneMedicationResponseDTO= new DroneMedicationResponseDTO();
	    List<MedicationDto> medicationList=new ArrayList<>();
	  
		DroneEntity droneEntity=droneEnt.get();
		Set<MedicationEntity> medications=droneEntity.getMedications();
		for(MedicationEntity meEnt:medications){
			MedicationDto medicationDto=new MedicationDto();
			medicationDto.setCode(meEnt.getCode());
			medicationDto.setName(meEnt.getName());
			medicationDto.setImage(meEnt.getImageUrl());
			medicationDto.setWeight(meEnt.getWeight());
			medicationList.add(medicationDto);
		}
		double weightSumofMedication=  medications.stream().filter(medication -> medication.getWeight() > 0).mapToDouble(medication -> medication.getWeight()).sum();
		droneMedicationResponseDTO.setId(droneEntity.getId());
		droneMedicationResponseDTO.setMedications(medicationList);
		droneMedicationResponseDTO.setTotal_weight(weightSumofMedication);
		
		
		return droneMedicationResponseDTO;
	}

	@Override
	public List<DroneEntity> getAllDrones() throws DroneStateException {
		List<DroneEntity> droneList=droneRepository.findAll();
		if(droneList.isEmpty()){
			throw new DroneStateException("No drones available at this time. Please try again later.");				
		}
		return droneList;
	}

	@Override
	public void logEvent(DroneEntity drone, String eventType) {
		 EventEntity event = new EventEntity();
		 event.setDrone(drone);
		 event.setEventType(eventType);
		 event.setTimestamp(LocalDateTime.now());
		 eventRepository.save(event);
	}

	@Override
	public List<DroneEntity> getAvilableDrones() throws DroneStateException {
		List<DroneEntity> droneList=droneRepository.findByStateAndBatteryCapacity();
		if(droneList.isEmpty()){
			throw new DroneStateException("No drones available at this time. Please try again later.");				
		}
		return droneList;
	}
	//Medication Data validation
	public boolean validateMedicationList(List<MedicationDto> medicationList){
		for(MedicationDto medicationDto :medicationList){
		
			if(!isValidName(medicationDto.getName())){
				return false;
			}
			if(!isValidCode(medicationDto.getCode())){
				return false;
			}
		}
		return true;
		
		
	}
	public boolean isValidName(String name) {

	    String regex = "^[a-zA-Z0-9_-]+$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(name);
	    return matcher.matches();
	}
	public boolean isValidCode(String code) {
	    String regex = "^[A-Z0-9_]+$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(code);
	    return matcher.matches();
	}
	
	
}
