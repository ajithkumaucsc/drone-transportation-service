package com.drone.transportation.dto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || value.toString().isEmpty()) {
            return true;
        }
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException(enumClass + " is not an enum type");
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equalsIgnoreCase(value.toString()));
    }
}
