package ru.kpfu.itis.barakhov.blablafly.validators;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.barakhov.blablafly.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.usernameUniqueness(s);
    }
}
