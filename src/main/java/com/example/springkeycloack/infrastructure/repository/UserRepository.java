package com.example.springkeycloack.infrastructure.repository;

import com.example.springkeycloack.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
