package ru.kpfu.itis.barakhov.blablafly.services;

import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;

import java.util.List;

public interface FlightsService {

    List<FlightDto> getAllFlights();

}
