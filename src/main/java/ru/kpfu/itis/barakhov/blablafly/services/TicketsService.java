package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.barakhov.blablafly.dto.TicketDto;

import java.util.List;

public interface TicketsService {

    void bookTicket(Long flightIg, UserDetails currentUser);

    List<TicketDto> findOwnedBy(UserDetails currentUser);

}
