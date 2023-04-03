Drone Transportation System
This project is a simple implementation of a drone transportation system.

Getting Started
To get started, clone the repository and open the project in your favorite IDE. You can also build and run the project from the command line using Maven.

Prerequisites
To run this project, you need to have the following installed on your machine:
-Java 8 or later
-Maven
-MySQL

To install the project dependencies, run the following command in the project directory:
 mvn install
DB configuration can change application.property file.
You can change the spring.jpa.hibernate.ddl-auto value.
spring.jpa.hibernate.ddl-auto=create

Running the Application
To run the application, use the following command:
 mvn spring-boot:run
Application running in 8080 port you can edit base path in application.property file.
Testing the Application
To test the application, you can use any REST client, such as Postman. You can also use the command line tool curl.

The following endpoints are available:
POST /api/v1/droneRegistration: Creates a new drone
POST /api/v1/loadMedicationByDroneID/{droneId}: Adds medications to a specific drone
GET /api/v1/getMedicationByDroneId/{droneId}: Returns a list of medication by specific drone.
GET /api/v1/getAvilableDrones: Returns a list of all avilable drones
GET /api/v1/checkBatteryLevelByDroneId/{droneId}: Returns the details of a specific drone

You can use the application/json media type to send and receive JSON payloads.Please find the postman collection Postman-Collection folder.

Built With
-Spring Boot
-Spring Data JPA
-Mysql Database
-Maven
Authors
[Ajith Kumara]
