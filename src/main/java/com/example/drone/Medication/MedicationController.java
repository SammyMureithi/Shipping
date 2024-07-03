package com.example.drone.Medication;

import com.example.drone.Drone.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/medication")
public class MedicationController {
    private final MedicationServices medicationServices;

    @Autowired
    public MedicationController(MedicationServices medicationServices) {
        this.medicationServices = medicationServices;
    }

    @PostMapping
    public ResponseEntity<Response> addMedication(@Valid  @RequestBody  Medication medication){
        return  medicationServices.addMedication(medication);
    }
}
