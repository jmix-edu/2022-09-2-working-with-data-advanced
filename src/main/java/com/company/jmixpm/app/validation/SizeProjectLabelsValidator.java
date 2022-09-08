package com.company.jmixpm.app.validation;

import com.company.jmixpm.app.datatype.ProjectLabels;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class SizeProjectLabelsValidator implements ConstraintValidator<SizeProjectLabels, ProjectLabels> {

    private int max;
    private int min;

    @Override
    public void initialize(SizeProjectLabels constraintAnnotation) {
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(ProjectLabels value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        List<String> labels = value.getLabels();

        return labels.size() >= min && labels.size() <= max;
    }
}
