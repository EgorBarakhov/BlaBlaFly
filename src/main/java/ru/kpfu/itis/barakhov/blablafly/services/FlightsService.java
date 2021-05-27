package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightForm;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightSearchForm;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;

import java.util.List;

public interface FlightsService {

    void bookTicket(Flight flight);

    void deleteFlight(Flight flight);

    void updateFlight(Flight flight, FlightForm flightForm);

    FlightForm convertToForm(Flight flight);

    Flight findById(Long id) throws IllegalArgumentException;

    List<FlightDto> searchFlights(FlightSearchForm flightSearchForm, Model model);

    FlightDto createFlight(FlightForm flightForm, UserDetails currentUser);
}
