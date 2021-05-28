package ru.kpfu.itis.barakhov.blablafly.dto;

import lombok.*;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FlightDto {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Long id;

    private Long departureTime;

    private Long arrivalTime;

    private String departureCity;

    private String arrivalCity;

    private Integer availablePlacesCount;

    private Float ticketPrice;

    private String ticketCurrency;

    private String aircraft;

    private String pilot;

    public static FlightDto from(Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .departureTime(flight.getDepartureTimeUtc())
                .departureCity(flight.getDepartureCity().getName())
                .arrivalTime(flight.getArrivalTimeUtc())
                .arrivalCity(flight.getArrivalCity().getName())
                .availablePlacesCount(flight.getAvailablePlacesCount())
                .ticketPrice(flight.getTicketPrice())
                .ticketCurrency(flight.getTicketCurrency().getDisplayName())
                .aircraft(flight.getAircraft().getName())
                .pilot(flight.getAircraft().getOwner().getUsername())
                .build();
    }

    public static List<FlightDto> from(List<Flight> flights) {
        return flights.stream()
                .map(FlightDto::from)
                .collect(Collectors.toList());
    }

    public String displayTime(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return simpleDateFormat.format(calendar.getTime());
    }
}
