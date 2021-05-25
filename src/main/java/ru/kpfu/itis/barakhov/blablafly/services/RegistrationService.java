package ru.kpfu.itis.barakhov.blablafly.services;

import ru.kpfu.itis.barakhov.blablafly.dto.forms.UserForm;

public interface RegistrationService {

    boolean signUp(UserForm form);

}
