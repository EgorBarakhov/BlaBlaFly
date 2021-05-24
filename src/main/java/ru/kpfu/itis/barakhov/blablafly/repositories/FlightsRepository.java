package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.barakhov.blablafly.models.City;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;

import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT flight FROM Flight flight WHERE flight.departureCity = :departureCity AND flight.arrivalCity = :arrivalCity AND (flight.departureTimeUtc + flight.departureCity.shiftFromUtc) > :departureTime")
    List<Flight> search(@Param("departureTime") Long departureTime,
                        @Param("departureCity") City departureCity,
                        @Param("arrivalCity") City arrivalCity);

}
