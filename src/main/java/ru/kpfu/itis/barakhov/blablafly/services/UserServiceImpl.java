package ru.kpfu.itis.barakhov.blablafly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.barakhov.blablafly.models.Role;
import ru.kpfu.itis.barakhov.blablafly.models.User;
import ru.kpfu.itis.barakhov.blablafly.repositories.RolesRepository;
import ru.kpfu.itis.barakhov.blablafly.repositories.UsersRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        return user;
    }

    @Override
    public boolean usernameUniqueness(String username) {
        return usersRepository.existsByUsername(username);
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> userFromDb = usersRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    @Override
    public List<User> allUsers() {
        return usersRepository.findAll();
    }

    @Override
    public boolean saveUser(User user) {
        User userFromDB = usersRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (usersRepository.findById(userId).isPresent()) {
            usersRepository.deleteById(userId);
            return true;
        }
        return false;
    }

}
