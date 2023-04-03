package com.drone.transportation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class DroneTransportation {
	
	public static void main(String[] args) {
		SpringApplication.run(DroneTransportation.class, args);
	}

}
