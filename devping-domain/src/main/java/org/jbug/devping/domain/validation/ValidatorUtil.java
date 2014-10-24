package org.jbug.devping.domain.validation;

import javax.validation.ConstraintValidatorContext;
import java.lang.IllegalAccessException;import java.lang.NoSuchFieldException;import java.lang.Object;import java.lang.String;import java.lang.reflect.Field;

public class ValidatorUtil {

    public static void addValidationError(String field, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addNode(field)
                .addConstraintViolation();
    }

    public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field f = object.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        return f.get(object);
    }
}
