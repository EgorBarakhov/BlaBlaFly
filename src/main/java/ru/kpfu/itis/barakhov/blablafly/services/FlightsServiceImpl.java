package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;
import ru.kpfu.itis.barakhov.blablafly.repositories.FlightRepository;

import java.util.List;

import static ru.kpfu.itis.barakhov.blablafly.dto.FlightDto.*;

@Service
public class FlightsServiceImpl implements FlightsService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<FlightDto> getAllFlights() {
        return from(flightRepository.findAll());
    }
}
