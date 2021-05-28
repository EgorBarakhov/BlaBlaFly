package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.barakhov.blablafly.dto.TicketDto;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;
import ru.kpfu.itis.barakhov.blablafly.models.Ticket;
import ru.kpfu.itis.barakhov.blablafly.models.User;
import ru.kpfu.itis.barakhov.blablafly.repositories.TicketsRepository;

import java.util.List;

import static ru.kpfu.itis.barakhov.blablafly.dto.TicketDto.*;

@Service
public class TicketsServiceImpl implements TicketsService {
    @Autowired
    private UserService userService;

    @Autowired
    private FlightsService flightsService;

    @Autowired
    private TicketsRepository ticketsRepository;

    @Override
    public void bookTicket(Long flightId, UserDetails currentUser) {
        Flight flight = flightsService.findById(flightId);
        flightsService.bookTicket(flight);
        User user = (User) userService.loadUserByUsername(currentUser.getUsername());
        Ticket ticket = Ticket.builder()
                .flight(flight)
                .holder(user)
                .build();
        ticketsRepository.save(ticket);
    }

    @Override
    public List<TicketDto> findOwnedBy(UserDetails currentUser) {
        return from(ticketsRepository.findByHolder(currentUser));
    }
}
