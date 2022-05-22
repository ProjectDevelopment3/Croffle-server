package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNaverId(Long naverId);
}
