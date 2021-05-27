package ru.kpfu.itis.barakhov.blablafly.dto;

import lombok.*;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FlightDto {

    private Long id;

    private Long departureTime;

    private Long arrivalTime;

    private String departureCity;

    private String arrivalCity;

    private Integer availablePlacesCount;

    private String aircraft;

    private String pilot;

    public static FlightDto from(Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .departureTime(flight.getDepartureTimeUtc())
                .arrivalTime(flight.getArrivalTimeUtc())
                .availablePlacesCount(flight.getAvailablePlacesCount())
                .departureCity(flight.getDepartureCity().getName())
                .arrivalCity(flight.getArrivalCity().getName())
                .aircraft(flight.getAircraft().getName())
                .pilot(flight.getAircraft().getOwner().getUsername())
                .build();
    }

    public static List<FlightDto> from(List<Flight> flights) {
        return flights.stream()
                .map(FlightDto::from)
                .collect(Collectors.toList());
    }

}
