package ru.kpfu.itis.barakhov.blablafly.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ru.kpfu.itis.barakhov.blablafly.models.City;
import ru.kpfu.itis.barakhov.blablafly.services.CitiesService;

public class StringToCityConverter implements Converter<String, City> {

    @Autowired
    private CitiesService citiesService;

    @Override
    public City convert(String s) {
        return citiesService.findByName(s);
    }

}
