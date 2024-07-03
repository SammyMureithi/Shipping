package com.example.drone.Shipment;

import com.example.drone.Drone.Drone;
import com.example.drone.Medication.Medication;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ShipmentRequest {
    @NotNull
    @Valid
    private Drone drone;

    @NotNull
    @Valid
    private Medication medication;

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }
}
