package ru.kpfu.itis.barakhov.blablafly.dto.forms;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;
import ru.kpfu.itis.barakhov.blablafly.models.Aircraft;
import ru.kpfu.itis.barakhov.blablafly.models.City;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;
import ru.kpfu.itis.barakhov.blablafly.services.CitiesService;

import javax.validation.constraints.NotNull;
import java.util.Currency;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightForm {

    private Long id;

    @NotNull(message = "Price must not be null")
    private Float ticketPrice;

    @NotNull(message = "Currency must not be empty")
    private Currency ticketCurrency;

    @NotNull(message = "Departure time must not be null")
    private Long departureTimeUtc;

    @NotNull(message = "Arrival time must not be null")
    private Long arrivalTimeUtc;

    private Integer availablePlacesCount;

    @NotNull(message = "Departure city must not be null")
    private City departureCity;

    @NotNull(message = "Arrival city must not be null")
    private City arrivalCity;

    @NotNull(message = "Aircraft must not be null")
    private Aircraft aircraft;

}
