package com.example.ticketing_system.infrastructure.adapter.in.validation;

import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestEventDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, RequestEventDTO> {
    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RequestEventDTO request, ConstraintValidatorContext context) {
        if (request.getEventDate() == null || request.getEventEndDate() == null) {
            return true; // Otra validación cubrirá el NotNull
        }

        boolean valid = request.getEventDate().isBefore(request.getEventEndDate());

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "{event.date.range.invalid}"
                    ).addPropertyNode("eventEndDate")
                    .addConstraintViolation();
        }

        return valid;
    }
}
