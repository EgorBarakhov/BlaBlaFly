package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.barakhov.blablafly.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
