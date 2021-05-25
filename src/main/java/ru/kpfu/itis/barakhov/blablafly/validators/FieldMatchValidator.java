package ru.kpfu.itis.barakhov.blablafly.validators;

import org.springframework.beans.BeanWrapperImpl;

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
            BeanWrapperImpl beanWrapper = new BeanWrapperImpl(o);
            final Object example = beanWrapper.getPropertyValue("example");
            final Object match = beanWrapper.getPropertyValue("match");

            return example == null && match == null || example != null && example.equals(match);
        } catch (final Exception exception) {
            // ignore
        }
        return true;
    }

}
