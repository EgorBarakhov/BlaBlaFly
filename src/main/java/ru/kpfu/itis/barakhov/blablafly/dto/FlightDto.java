package ru.kpfu.itis.barakhov.blablafly.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.kpfu.itis.barakhov.blablafly.models.City;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class FlightDto {

    private Long departureTime;

    private City departureCity;

    private City arrivalCity;

    private Integer availablePlacesCount;

    private String aircraftName;

    private String aircraftOwnerName;

    public static FlightDto from(Flight flight) {
        return FlightDto.builder()
                .departureTime(flight.getDepartureTimeUtc())
                .availablePlacesCount(flight.getAvailablePlacesCount())
                .departureCity(flight.getDepartureCity())
                .arrivalCity(flight.getArrivalCity())
                .aircraftName(flight.getAircraft().getName())
                .aircraftOwnerName(flight.getAircraft().getOwner().getUsername())
                .build();
    }

    public static List<FlightDto> from(List<Flight> flights) {
        return flights.stream()
                .map(FlightDto::from)
                .collect(Collectors.toList());
    }

}
