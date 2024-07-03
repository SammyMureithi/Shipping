package com.example.drone.Shipment;

import com.example.drone.Drone.Drone;
import com.example.drone.Drone.DroneResponse;
import com.example.drone.Drone.Response;
import com.example.drone.Medication.Medication;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/shipment")
public class ShipmentController {
    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }
    @GetMapping(path = "/available_drone")
    public ResponseEntity<?> getAvailableDrones(){
        return shipmentService.getAvailableDrones();
    }
    @PostMapping
    public  ResponseEntity<?> processShipment( @RequestBody ShipmentRequest request){
        return  shipmentService.startShipping(request.getDrone(),request.getMedication());
    }
}
