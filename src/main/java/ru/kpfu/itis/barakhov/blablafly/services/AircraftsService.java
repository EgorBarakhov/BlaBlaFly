package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.barakhov.blablafly.dto.AircraftDto;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.AircraftForm;
import ru.kpfu.itis.barakhov.blablafly.models.Aircraft;

import java.util.List;

public interface AircraftsService {

    void createAircraft(AircraftForm aircraftForm, UserDetails userDetails);

    Aircraft findByName(String name);

    List<AircraftDto> findOwnedBy(UserDetails currentUser);
}
