package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.barakhov.blablafly.models.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
