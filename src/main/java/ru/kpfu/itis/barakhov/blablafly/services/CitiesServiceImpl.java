package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.barakhov.blablafly.dto.CityDto;
import ru.kpfu.itis.barakhov.blablafly.models.City;
import ru.kpfu.itis.barakhov.blablafly.repositories.CitiesRepository;

import java.util.List;

import static ru.kpfu.itis.barakhov.blablafly.dto.CityDto.*;

@Service
public class CitiesServiceImpl implements CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;

    @Override
    public List<CityDto> findAll() {
        return from(citiesRepository.findAll());
    }

    @Override
    public City findByName(String name) {
        return citiesRepository.findByName(name);
    }
}
