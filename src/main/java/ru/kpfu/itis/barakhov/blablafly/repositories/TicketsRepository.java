package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.barakhov.blablafly.models.Ticket;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByHolder(UserDetails currentUser);

}
