package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightSearchForm;
import ru.kpfu.itis.barakhov.blablafly.repositories.FlightsRepository;

import java.util.List;

import static ru.kpfu.itis.barakhov.blablafly.dto.FlightDto.*;

@Service
public class FlightsServiceImpl implements FlightsService {

    @Autowired
    private CitiesService citiesService;

    @Autowired
    private FlightsRepository flightsRepository;

    @Override
    public List<FlightDto> searchFlights(FlightSearchForm flight, Model model) {
        if (flight == null ||
                (flight.getDepartureCity() == null && flight.getArrivalCity() == null && flight.getDepartureTime() == null)) {
            return from(flightsRepository.findAll());
        }

        if (flight.getDepartureCity() == null || flight.getArrivalCity() == null) {
            model.addAttribute("cityError", "Please, specify cities");
            return from(flightsRepository.findAll());
        }

        if (flight.getDepartureTime() == null) {
            flight.setDepartureTime(System.currentTimeMillis());
        }

        return from(flightsRepository.search(
                transposeDate(flight.getDepartureTime(), flight.getDepartureCity()),
                flight.getDepartureCity(),
                flight.getArrivalCity()
        ));
    }

    private Long transposeDate(Long timestamp, String city) {
        return (timestamp + citiesService.findByName(city).getShiftFromUtc());
    }

}
