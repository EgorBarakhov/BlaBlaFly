package ru.kpfu.itis.barakhov.blablafly.dto;

import lombok.*;
import ru.kpfu.itis.barakhov.blablafly.models.City;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CityDto {

    private String name;

    public static CityDto from(City city) {
        return CityDto.builder()
                .name(city.getName())
                .build();
    }

    public static List<CityDto> from(List<City> cities) {
        return cities.stream()
                .map(CityDto::from)
                .collect(Collectors.toList());
    }

}
