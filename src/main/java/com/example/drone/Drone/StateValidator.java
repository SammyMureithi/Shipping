package com.example.drone.Drone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class StateValidator implements ConstraintValidator<ValidState, String> {

    private Set<String> validState;

    @Override
    public void initialize(ValidState constraintAnnotation) {
        validState= Arrays.stream(DroneState.values())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && validState.contains(value);
    }
}