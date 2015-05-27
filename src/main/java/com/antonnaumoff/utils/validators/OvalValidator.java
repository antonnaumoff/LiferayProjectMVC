package com.antonnaumoff.utils.validators;


import com.antonnaumoff.models.Department;
import com.antonnaumoff.models.Employee;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.exception.ValidationFailedException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OvalValidator {

    public Map<String, String> validate(Employee validatedObject) throws IllegalArgumentException, ValidationFailedException {

        HashMap<String, String> map = new HashMap<String, String>();
        Validator valid = new Validator();
        List<ConstraintViolation> violations = valid.validate(validatedObject);
        if (violations.size() > 0) {
            for (ConstraintViolation vio : violations) {
                map.put((((FieldContext) vio.getContext()).getField().getName()), vio.getMessage());
            }
        }
        return map;
    }

    public Map<String, String> validate(Department validatedObject) throws IllegalArgumentException, ValidationFailedException {

        HashMap<String, String> map = new HashMap<String, String>();
        Validator valid = new Validator();
        List<ConstraintViolation> violations = valid.validate(validatedObject);
        if (violations.size() > 0) {
            for (ConstraintViolation vio : violations) {
                map.put((((FieldContext) vio.getContext()).getField().getName()), vio.getMessage());
            }
        }
        return map;
    }


}
