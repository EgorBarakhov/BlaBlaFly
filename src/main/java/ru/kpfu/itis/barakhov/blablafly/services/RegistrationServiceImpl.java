package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.barakhov.blablafly.dto.UserForm;
import ru.kpfu.itis.barakhov.blablafly.models.User;


@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserService userService;

    @Override
    public boolean signUp(UserForm form) {
        User user = User.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .confirmPassword(form.getConfirmPassword())
                .build();
        return userService.saveUser(user);
    }

}
