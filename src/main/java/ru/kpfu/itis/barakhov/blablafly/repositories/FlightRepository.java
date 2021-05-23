package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
