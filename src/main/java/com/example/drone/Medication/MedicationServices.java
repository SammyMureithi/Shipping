package com.example.drone.Medication;

import com.example.drone.Drone.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicationServices {
    private final MedicationRepository medicationRepository;

    public MedicationServices(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }
    public ResponseEntity<Response> addMedication(Medication medication){
        try {
            var addediTEM=medicationRepository.save(medication);
            System.out.println(addediTEM);
            return  new ResponseEntity<>( new Response(true,"success","Medication added successfully"), HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(new Response(false, "error", "Failed to create medication: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
  }
}
