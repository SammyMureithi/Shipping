package com.example.drone.Shipment;

import com.example.drone.Drone.Drone;
import com.example.drone.Medication.Medication;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Shipment {
    @Id
    @SequenceGenerator(
            name="shipment_sequence",
            sequenceName="shipment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shipment_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone droneId;
    private String state;
    @ManyToOne
    @JoinColumn(name = "medicineId" , nullable = false)
    private Medication medicineId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drone getDroneId() {
        return droneId;
    }

    public void setDroneId(Drone droneId) {
        this.droneId = droneId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Medication getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Medication medicineId) {
        this.medicineId = medicineId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Shipment() {
    }

    public Shipment(Drone droneId, Long id, String state, Medication medicineId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.droneId = droneId;
        this.id = id;
        this.state = state;
        this.medicineId = medicineId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
