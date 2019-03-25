package org.andres_k.web.backend.models.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> getAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
