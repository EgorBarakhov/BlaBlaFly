package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightForm;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightSearchForm;
import ru.kpfu.itis.barakhov.blablafly.models.City;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;
import ru.kpfu.itis.barakhov.blablafly.repositories.FlightsRepository;

import java.util.List;
import java.util.Optional;

import static ru.kpfu.itis.barakhov.blablafly.dto.FlightDto.*;

@Service
public class FlightsServiceImpl implements FlightsService {

    @Autowired
    private FlightsRepository flightsRepository;

    @Override
    public void bookTicket(Flight flight) {
        flightsRepository.decrementAvailablePlacesCount(flight);
    }

    @Override
    public void deleteFlight(Flight flight) {
        flightsRepository.delete(flight);
    }

    @Override
    public void updateFlight(Flight flight, FlightForm flightForm) {
        flightsRepository.update(
                flight.getId(),
                flightForm.getDepartureCity(),
                flightForm.getDepartureTimeUtc(),
                flightForm.getArrivalCity(),
                flightForm.getArrivalTimeUtc(),
                flightForm.getAircraft(),
                flightForm.getAvailablePlacesCount(),
                flightForm.getTicketPrice(),
                flightForm.getTicketCurrency().getCurrencyCode()
        );
    }

    @Override
    public FlightForm convertToForm(Flight flight) {
        return FlightForm.builder()
                .id(flight.getId())
                .aircraft(flight.getAircraft())
                .departureCity(flight.getDepartureCity())
                .arrivalCity(flight.getArrivalCity())
                .arrivalTimeUtc(flight.getArrivalTimeUtc())
                .departureTimeUtc(flight.getDepartureTimeUtc())
                .availablePlacesCount(flight.getAvailablePlacesCount())
                .ticketPrice(flight.getTicketPrice())
                .ticketCurrency(flight.getTicketCurrency())
                .build();
    }

    @Override
    public Flight findById(Long id) throws IllegalArgumentException {
        Optional<Flight> flight = flightsRepository.findById(id);
        if (flight.isPresent()) {
            return flight.get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FlightDto createFlight(FlightForm flightForm, UserDetails currentUser) {
        Flight flight = Flight.builder()
                .departureTimeUtc(flightForm.getDepartureTimeUtc())
                .arrivalTimeUtc(flightForm.getArrivalTimeUtc())
                .departureCity(flightForm.getDepartureCity())
                .arrivalCity(flightForm.getArrivalCity())
                .availablePlacesCount(flightForm.getAvailablePlacesCount())
                .ticketPrice(flightForm.getTicketPrice())
                .ticketCurrency(flightForm.getTicketCurrency().getCurrencyCode())
                .aircraft(flightForm.getAircraft())
                .build();
        flightsRepository.save(flight);
        return from(flight);
    }

    @Override
    public List<FlightDto> searchFlights(FlightSearchForm flightSearchForm, Model model) {
        if (formNotFilled(flightSearchForm)) {
            return from(flightsRepository.findAll());
        }

        if (cityIsNotSpecified(flightSearchForm)) {
            model.addAttribute("error", "Specify cities to search");
            return from(flightsRepository.findAll());
        }

        if (flightSearchForm.getDepartureTime() == null) {
            flightSearchForm.setDepartureTime(System.currentTimeMillis());
        }

        return from(flightsRepository.search(
                transposeDate(flightSearchForm.getDepartureTime(), flightSearchForm.getDepartureCity()),
                flightSearchForm.getDepartureCity().getName(),
                flightSearchForm.getArrivalCity().getName()
        ));
    }

    private Long transposeDate(Long timestamp, City city) {
        return (timestamp + city.getShiftFromUtc());
    }

    private boolean formNotFilled(FlightSearchForm flightSearchForm) {
        return flightSearchForm.getDepartureCity() == null &&
                flightSearchForm.getArrivalCity() == null &&
                flightSearchForm.getDepartureTime() == null;
    }

    private boolean cityIsNotSpecified(FlightSearchForm flightSearchForm) {
        return flightSearchForm.getDepartureCity() == null ||
                flightSearchForm.getArrivalCity() == null;
    }
}
