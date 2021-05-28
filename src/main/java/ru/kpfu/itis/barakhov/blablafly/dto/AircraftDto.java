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

    private String legacySerialNumber;

    private Integer capacity;

    public static AircraftDto from(Aircraft aircraft) {
        return AircraftDto.builder()
                .name(aircraft.getName())
                .legacySerialNumber(aircraft.getLegacySerialNumber())
                .capacity(aircraft.getCapacity())
                .build();
    }

    public static List<AircraftDto> from(List<Aircraft> aircrafts) {
        return aircrafts.stream()
                .map(AircraftDto::from)
                .collect(Collectors.toList());
    }

}
