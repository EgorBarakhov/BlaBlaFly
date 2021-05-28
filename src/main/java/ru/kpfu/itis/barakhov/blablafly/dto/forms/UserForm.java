package ru.kpfu.itis.barakhov.blablafly.dto.forms;

import lombok.*;
import ru.kpfu.itis.barakhov.blablafly.validators.FieldMatch;
import ru.kpfu.itis.barakhov.blablafly.validators.UniqueUsername;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(example = "password", match = "confirmPassword", message = "Password do not match")
public class UserForm {
    @NotNull
    @Size(min = 5, max = 20, message = "Username should be at least 5 and no more than 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]*([._-](?![._-])|[a-zA-Z0-9])*[a-zA-Z0-9]$", message = "Invalid symbols in username")
    @UniqueUsername
    private String username;

    @NotNull
    @Size(min = 5, max = 20, message = "Password should be at least 5 and no more than 20 characters")
    private String password;

    @NotEmpty(message = "Confirm password")
    private String confirmPassword;
}
