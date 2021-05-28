package ru.kpfu.itis.barakhov.blablafly.dto.forms;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AircraftForm {
    @Column(unique = true)
    @NotNull(message = "Name must not be null")
    private String name;

    @Column(unique = true)
    @NotNull(message = "Legacy Serial Number must not be null")
    private String legacySerialNumber;

    @NotNull(message = "Capacity must not be null")
    private Integer capacity;
}
