package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.ui.Model;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightSearchForm;

import java.util.List;

public interface FlightsService {

    List<FlightDto> searchFlights(FlightSearchForm flight, Model model);

}
