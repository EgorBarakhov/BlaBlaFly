package ru.kpfu.itis.barakhov.blablafly.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.barakhov.blablafly.validators.FieldMatch;
import ru.kpfu.itis.barakhov.blablafly.validators.UniqueUsername;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(example = "password", match = "confirmPassword", message = "Password do not match")
public class UserForm {

    @NotNull
    @Size(min = 5, max = 20, message = "At least 5 and no more than 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "Invalid username")
    @UniqueUsername
    private String username;

    @NotNull
    @Size(min = 5, max = 20, message = "At least 5 and no more than 20 characters")
    private String password;

    private String confirmPassword;

}