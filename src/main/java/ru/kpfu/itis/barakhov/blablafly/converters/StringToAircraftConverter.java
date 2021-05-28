package ru.kpfu.itis.barakhov.blablafly.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ru.kpfu.itis.barakhov.blablafly.models.Aircraft;
import ru.kpfu.itis.barakhov.blablafly.services.AircraftsService;

public class StringToAircraftConverter implements Converter<String, Aircraft> {
    @Autowired
    private AircraftsService aircraftsService;

    @Override
    public Aircraft convert(String s) {
        return aircraftsService.findByName(s);
    }
}
