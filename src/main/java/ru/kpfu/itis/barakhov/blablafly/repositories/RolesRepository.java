package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.barakhov.blablafly.models.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
}
