package io.imob.domain.repository;

import io.imob.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CustomJpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
