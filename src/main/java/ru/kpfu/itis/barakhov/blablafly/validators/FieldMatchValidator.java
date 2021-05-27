package ru.kpfu.itis.barakhov.blablafly.validators;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String example;
    private String match;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        example = constraintAnnotation.example();
        match = constraintAnnotation.match();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            final Object exampleObject = BeanUtils.getProperty(o, example);
            final Object matchObject = BeanUtils.getProperty(o, match);

            return exampleObject == null && matchObject == null ||
                    exampleObject != null && exampleObject.equals(matchObject);
        } catch (final Exception exception) {
            // ignore
        }
        return true;
    }

}
