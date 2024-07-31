package com.phatdo.authorizationserver.repositories;

import java.util.Optional;

import com.phatdo.authorizationserver.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
