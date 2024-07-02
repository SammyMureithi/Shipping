package com.example.drone.Drone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelValidator implements ConstraintValidator<ValidModel, String> {

    private Set<String> validModels;

    @Override
    public void initialize(ValidModel constraintAnnotation) {
        validModels = Arrays.stream(DroneModel.values())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && validModels.contains(value);
    }
}
