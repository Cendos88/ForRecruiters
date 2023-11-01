package com.myapp.georgewannabe.repositories;

import com.myapp.georgewannabe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findById(Long id);
}
