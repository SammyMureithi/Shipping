package com.example.drone.Shipment;

import com.example.drone.Drone.*;
import com.example.drone.Medication.Medication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private  final DroneRepository droneRepository;

    public ShipmentService(ShipmentRepository shipmentRepository, DroneRepository droneRepository) {
        this.shipmentRepository = shipmentRepository;
        this.droneRepository = droneRepository;
    }

    public ResponseEntity<?> getAvailableDrones(){
        try {
            List<Drone> idleDrones=droneRepository.findAllIdleDrone("IDLE");

            return new ResponseEntity<>( new DroneResponse(true,"success",idleDrones), HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(new Response(false, "error", "Failed to fetch available drones: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
  }

    public ResponseEntity<?> startShipping(Drone drone, Medication medication) {
        try {
            // Check the medication weight compared to the drone's weight
            if (drone.getWeight() < medication.getWeight()) {
                return new ResponseEntity<>(new Response(false, "error", "The drone cannot ship the medication"), HttpStatus.BAD_REQUEST);
            }

            // Change the state to LOADING and initiate the shipment process
            Optional<Drone> droneToShip = droneRepository.findById(drone.getId());
            if (droneToShip.isPresent()) {
                Drone foundDrone = droneToShip.get();
                foundDrone.setState(DroneState.LOADING.name());
                droneRepository.save(foundDrone);

                Shipment shipment = new Shipment();
                shipment.setDroneId(foundDrone);
                shipment.setMedicineId(medication);
                shipment.setState(DroneState.LOADING.name());
                shipmentRepository.save(shipment);

                // Schedule state transitions asynchronously
                scheduleStateTransitions(foundDrone, shipment);

                return new ResponseEntity<>(new Response(true, "success", "Drone is now back"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response(false, "error", "Drone not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, "error", "Failed to process shipping: " + e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Async
    public void scheduleStateTransitions(Drone drone, Shipment shipment) {
        try {
            List<DroneState> states = Arrays.asList(
                    DroneState.LOADING,
                    DroneState.LOADED,
                    DroneState.DELIVERING,
                    DroneState.DELIVERED,
                    DroneState.RETURNING,
                    DroneState.IDLE
            );

            for (DroneState state : states) {
                Thread.sleep(30000); // 0.5 minutes
                updateDroneAndShipmentState(drone, shipment, state);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateDroneAndShipmentState(Drone drone, Shipment shipment, DroneState newState) {
        drone.setState(newState.name());
        droneRepository.save(drone);

        shipment.setState(newState.name());
        shipmentRepository.save(shipment);
    }
}
