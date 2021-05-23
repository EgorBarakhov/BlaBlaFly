package ru.kpfu.itis.barakhov.blablafly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.barakhov.blablafly.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
