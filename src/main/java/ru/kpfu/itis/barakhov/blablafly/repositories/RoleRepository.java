package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.barakhov.blablafly.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
