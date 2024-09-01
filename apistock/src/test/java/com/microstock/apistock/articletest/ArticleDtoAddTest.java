package com.microstock.apistock.articletest;

import com.microstock.apistock.infraestructur.driving_http.dtos.request.ArticleDtoAdd;
import com.microstock.apistock.infraestructur.util.ConstantsInfraestructure;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArticleDtoAddTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidArticleDtoAdd() {
        // Creating a valid ArticleDtoAdd instance
        ArticleDtoAdd article = new ArticleDtoAdd(
            "Valid Name", 
            "Valid description", 
            100.0, 
            10, 
            1, 
            List.of(1, 2)
        );

        // Validating the instance
        Set<ConstraintViolation<ArticleDtoAdd>> violations = validator.validate(article);

        // Asserting that there are no violations
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidName() {
        // Creating an instance with an invalid name (blank)
        ArticleDtoAdd article = new ArticleDtoAdd(
            "  ", 
            "Valid description", 
            100.0, 
            10, 
            1, 
            List.of(1, 2)
        );

        Set<ConstraintViolation<ArticleDtoAdd>> violations = validator.validate(article);

        // Asserting that there's exactly one violation and it's related to the name
        assertEquals(1, violations.size());
        assertEquals(ConstantsInfraestructure.ERROR_NAME_NULL, violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidDescription() {
        // Creating an instance with an invalid description (blank)
        ArticleDtoAdd article = new ArticleDtoAdd(
            "Valid Name", 
            "  ", 
            100.0, 
            10, 
            1, 
            List.of(1, 2)
        );

        Set<ConstraintViolation<ArticleDtoAdd>> violations = validator.validate(article);

        // Asserting that there's exactly one violation and it's related to the description
        assertEquals(1, violations.size());
        assertEquals(ConstantsInfraestructure.ERROR_DESCRIPTION_NULL, violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidPrice() {
        // Creating an instance with an invalid price (negative)
        ArticleDtoAdd article = new ArticleDtoAdd(
            "Valid Name", 
            "Valid description", 
            -100.0, 
            10, 
            1, 
            List.of(1, 2)
        );

        Set<ConstraintViolation<ArticleDtoAdd>> violations = validator.validate(article);

        // Asserting that there's exactly one violation and it's related to the price
        assertEquals(1, violations.size());
        assertEquals(ConstantsInfraestructure.ERROR_PRICE, violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidQuantity() {
        // Creating an instance with an invalid quantity (negative)
        ArticleDtoAdd article = new ArticleDtoAdd(
            "Valid Name", 
            "Valid description", 
            100.0, 
            -1, 
            1, 
            List.of(1, 2)
        );

        Set<ConstraintViolation<ArticleDtoAdd>> violations = validator.validate(article);

        // Asserting that there's exactly one violation and it's related to the quantity
        assertEquals(1, violations.size());
        assertEquals(ConstantsInfraestructure.ERROR_QUANTITY, violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidCategoriesId() {
        // Creating an instance with an invalid categoriesId (more of 3)
        ArticleDtoAdd article = new ArticleDtoAdd(
            "Valid Name", 
            "Valid description", 
            100.0, 
            10, 
            1, 
            List.of(1,2,3,4) 
        );

        Set<ConstraintViolation<ArticleDtoAdd>> violations = validator.validate(article);

        // Asserting that there's exactly one violation and it's related to the categoriesId
        assertEquals(1, violations.size());
        assertEquals(ConstantsInfraestructure.ERROR_CATEGORIES_1TO3, violations.iterator().next().getMessage());
    }
}
