package com.example.drone.Drone;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneServices {
    private  final  DroneRepository droneRepository;

    @Autowired
    public DroneServices(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Transactional
    public ResponseEntity<Response> addDrone(Drone drone) {
        try {
            String serialNumber = drone.getSerialNumber();
            if (serialNumber == null) {
                return new ResponseEntity<>(new Response(false, "error", "Serial Number cannot be null"), HttpStatus.BAD_REQUEST);
            }

            Optional<Drone> droneBySerialNumber = droneRepository.findDroneBySerialNumber(serialNumber);
            if (droneBySerialNumber.isPresent()) {
                return new ResponseEntity<>(new Response(false, "error", "Serial Number Taken"), HttpStatus.BAD_REQUEST);
            }

            droneRepository.save(drone);
            return new ResponseEntity<>(new Response(true, "success", "Drone created successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, "error", "Failed to create drone: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
