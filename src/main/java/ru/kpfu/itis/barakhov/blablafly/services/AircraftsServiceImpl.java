package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.barakhov.blablafly.dto.AircraftDto;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.AircraftForm;
import ru.kpfu.itis.barakhov.blablafly.models.Aircraft;
import ru.kpfu.itis.barakhov.blablafly.models.User;
import ru.kpfu.itis.barakhov.blablafly.repositories.AircraftsRepository;

import java.util.List;

import static ru.kpfu.itis.barakhov.blablafly.dto.AircraftDto.*;

@Service
public class AircraftsServiceImpl implements AircraftsService {

    @Autowired
    private AircraftsRepository aircraftsRepository;

    @Override
    public void createAircraft(AircraftForm aircraftForm, UserDetails currentUser) {
        Aircraft aircraft = Aircraft.builder()
                .owner((User) currentUser)
                .capacity(aircraftForm.getCapacity())
                .name(aircraftForm.getName())
                .legacySerialNumber(aircraftForm.getLegacySerialNumber())
                .build();
        aircraftsRepository.save(aircraft);
    }

    @Override
    public List<AircraftDto> findOwnedBy(UserDetails currentUser) {
        return from(aircraftsRepository.findByOwner(currentUser));
    }

    @Override
    public Aircraft findByName(String name) {
        return aircraftsRepository.findByName(name);
    }
}
