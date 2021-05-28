package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.barakhov.blablafly.models.Aircraft;
import ru.kpfu.itis.barakhov.blablafly.models.City;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;

import java.util.Currency;
import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<Flight, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Flight flight SET flight.availablePlacesCount = flight.availablePlacesCount - 1")
    void decrementAvailablePlacesCount(Flight flight);

    @Transactional
    @Modifying
    @Query("UPDATE Flight flight SET flight.departureCity = :departureCity, flight.departureTimeUtc = :departureTimeUtc, flight.arrivalCity = :arrivalCity, flight.arrivalTimeUtc = :arrivalTimeUtc, flight.aircraft = :aircraft, flight.availablePlacesCount = :availablePlacesCount, flight.ticketPrice = :ticketPrice, flight.ticketCurrency = :ticketCurrency WHERE flight.id = :id")
    void update(
            Long id,
            City departureCity,
            Long departureTimeUtc,
            City arrivalCity,
            Long arrivalTimeUtc,
            Aircraft aircraft,
            Integer availablePlacesCount,
            Float ticketPrice,
            String ticketCurrency);

    @Query("SELECT flight FROM Flight flight WHERE flight.departureCity.name = :departureCity AND flight.arrivalCity.name = :arrivalCity AND (flight.departureTimeUtc + flight.departureCity.shiftFromUtc) > :departureTime")
    List<Flight> search(Long departureTime,
                        String departureCity,
                        String arrivalCity);

}
