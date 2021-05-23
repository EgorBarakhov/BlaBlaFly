package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.barakhov.blablafly.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
