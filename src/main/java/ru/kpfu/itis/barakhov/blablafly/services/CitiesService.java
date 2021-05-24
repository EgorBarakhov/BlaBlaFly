package ru.kpfu.itis.barakhov.blablafly.services;

import ru.kpfu.itis.barakhov.blablafly.dto.CityDto;
import ru.kpfu.itis.barakhov.blablafly.models.City;

import java.util.List;

public interface CitiesService {

    List<CityDto> findAll();

    City findByName(String name);
}
