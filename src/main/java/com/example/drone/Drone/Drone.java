package com.example.drone.Drone;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table
public class Drone {
    @Id
    @SequenceGenerator(
            name="drone_sequence",
            sequenceName="drone_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "drone_sequence"
    )
    private Long id;
    @NotBlank(message = "Serial number is mandatory")
    @Column(name = "serial_number", unique = true)
    @Size(max = 100, message = "Serial number must be 100 characters or less")
    private String serialNumber;

    @NotBlank(message = "Model is mandatory")
    @ValidModel
    private String model;

    @Positive(message = "Weight must be positive")
    @Max(value = 500, message = "Weight cannot be more than 500")
    private Integer weight;

    @Positive(message = "Battery must be positive")
    private Integer battery;

    @NotBlank(message = "State is mandatory")
    private String state;

    public Drone() {
    }

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Serial number is mandatory") @Size(max = 100, message = "Serial number must be 100 characters or less") String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(@NotBlank(message = "Serial number is mandatory") @Size(max = 100, message = "Serial number must be 100 characters or less") String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public @NotBlank(message = "Model is mandatory") String getModel() {
        return model;
    }

    public void setModel(@NotBlank(message = "Model is mandatory") String model) {
        this.model = model;
    }

    public @Positive(message = "Weight must be positive") @Max(value = 500, message = "Weight cannot be more than 500") Integer getWeight() {
        return weight;
    }

    public void setWeight(@Positive(message = "Weight must be positive") @Max(value = 500, message = "Weight cannot be more than 500") Integer weight) {
        this.weight = weight;
    }

    public @Positive(message = "Battery must be positive") Integer getBattery() {
        return battery;
    }

    public void setBattery(@Positive(message = "Battery must be positive") Integer battery) {
        this.battery = battery;
    }

    public @NotBlank(message = "State is mandatory") String getState() {
        return state;
    }

    public void setState(@NotBlank(message = "State is mandatory") String state) {
        this.state = state;
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

    public Drone(Long id,
                 String serialNumber,
                 String model,
                 Integer weight,
                 Integer battery,
                 String state,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.battery = battery;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
