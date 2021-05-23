package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;
import ru.kpfu.itis.barakhov.blablafly.repositories.FlightsRepository;

import java.util.List;

import static ru.kpfu.itis.barakhov.blablafly.dto.FlightDto.*;

@Service
public class FlightsServiceImpl implements FlightsService {

    @Autowired
    private FlightsRepository flightsRepository;

    @Override
    public List<FlightDto> getAllFlights() {
        return from(flightsRepository.findAll());
    }
}
