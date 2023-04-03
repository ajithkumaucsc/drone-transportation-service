package com.drone.transportation.audit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.drone.transportation.entity.DroneEntity;
import com.drone.transportation.exception.DroneStateException;
import com.drone.transportation.services.DroneService;

@Component
public class DroneBatteryChecker {
    
    private final DroneService droneService;
    @Autowired
    public DroneBatteryChecker(DroneService droneService) {
        this.droneService = droneService;
    }
    
    @Scheduled(fixedDelay = 600000) // check every 10 minute
    public void checkBatteryLevel() throws DroneStateException {
    	System.out.println("-----schedular runing---------");
        List<DroneEntity> dronesList = droneService.getAllDrones();
        for (DroneEntity drone : dronesList) {
            if (drone.getBatteryCapacity() < 25) {
                // log the event
                droneService.logEvent(drone, "LOW_BATTERY");
            }
        }
    }
}
