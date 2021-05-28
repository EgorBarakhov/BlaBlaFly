package ru.kpfu.itis.barakhov.blablafly.dto.forms;

import lombok.*;
import ru.kpfu.itis.barakhov.blablafly.models.City;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchForm {

    private Long departureTime;

    private City departureCity;

    private City arrivalCity;
}
