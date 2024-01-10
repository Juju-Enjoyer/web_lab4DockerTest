package ru.itm.lab.web_lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itm.lab.web_lab4.entity.Point;
import ru.itm.lab.web_lab4.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean existsUserByUsername(String username);
}
