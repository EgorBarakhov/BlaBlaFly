package ru.kpfu.itis.barakhov.blablafly.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class FlightSearchForm {

    private Long departureTime;

    @NotNull
    private String departureCity;

    @NotNull
    private String arrivalCity;

}
