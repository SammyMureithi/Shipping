package com.example.drone.Drone;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/drone")
public class DroneController {
    private  final DroneServices droneServices;
    @Autowired
    public DroneController(DroneServices droneServices) {
        this.droneServices = droneServices;
    }

    @PostMapping
    public ResponseEntity<Response> addDrone(@Valid @RequestBody Drone drone){
        return  droneServices.addDrone(drone);
    }


}
