package ru.kpfu.itis.barakhov.blablafly.dto;

import lombok.*;
import ru.kpfu.itis.barakhov.blablafly.models.Aircraft;
import ru.kpfu.itis.barakhov.blablafly.models.City;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AircraftDto {

    private String name;

    public static AircraftDto from(Aircraft aircraft) {
        return AircraftDto.builder()
                .name(aircraft.getName())
                .build();
    }

    public static List<AircraftDto> from(List<Aircraft> aircrafts) {
        return aircrafts.stream()
                .map(AircraftDto::from)
                .collect(Collectors.toList());
    }

}
