package ru.kpfu.itis.barakhov.blablafly.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    public static FlightDto from(Flight flight) {
        return FlightDto.builder()
                .build();
    }

    public static List<FlightDto> from(List<Flight> flights) {
        return flights.stream()
                .map(FlightDto::from)
                .collect(Collectors.toList());
    }
}
