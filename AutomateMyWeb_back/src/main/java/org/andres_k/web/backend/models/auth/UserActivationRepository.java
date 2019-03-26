package org.andres_k.web.backend.models.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserActivationRepository extends JpaRepository<UserActivation, Long> {
    Optional<UserActivation> findByIdentifier(String identifier);
    void deleteAllByUserId(Long userId);
}
