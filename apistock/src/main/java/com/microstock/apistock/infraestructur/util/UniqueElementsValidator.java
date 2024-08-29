package com.microstock.apistock.infraestructur.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.microstock.apistock.domain.util.IUniqueElements;

public class UniqueElementsValidator implements ConstraintValidator<IUniqueElements, List<?>> {

    @Override
    public void initialize(IUniqueElements constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext context) {
        if (list == null) {
            return true; 
        }

        Set<Object> set = new HashSet<>(list);
        return set.size() == list.size();
    }
}