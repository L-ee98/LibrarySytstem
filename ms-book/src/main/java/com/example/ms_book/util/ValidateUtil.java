package com.example.ms_book.util;

import com.example.ms_book.enums.BookError;
import com.example.ms_book.exception.GenericBadException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class ValidateUtil {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> void validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int counter = 0;
            for (ConstraintViolation<T> constraintViolation : violations) {
                sb.append(constraintViolation.getPropertyPath()).append(": ").append(constraintViolation.getMessage());
                counter++;
                if (counter < violations.size())
                    sb.append("; ");
            }
            throw new GenericBadException(BookError.VALIDATION_ERROR.getCode(), BookError.VALIDATION_ERROR.getDescription().replace("{}", sb.toString()));
        }
    }
}
