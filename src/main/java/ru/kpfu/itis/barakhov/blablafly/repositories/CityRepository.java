package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.barakhov.blablafly.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
