package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.ui.Model;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;

import java.util.List;

public interface FlightsService {

    List<FlightDto> searchFlights(FlightDto flight, Model model);

}
