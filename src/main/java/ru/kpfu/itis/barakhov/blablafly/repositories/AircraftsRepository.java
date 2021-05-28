package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.barakhov.blablafly.models.Aircraft;

import java.util.List;

@Repository
public interface AircraftsRepository extends JpaRepository<Aircraft, Long> {

    Aircraft findByName(String name);

    List<Aircraft> findByOwner(UserDetails owner);
}
