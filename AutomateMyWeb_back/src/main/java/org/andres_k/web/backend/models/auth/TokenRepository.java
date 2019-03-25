package org.andres_k.web.backend.models.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token getByUserIdAndAndExpiredIsFalse(Long userId);
    Token getByValue(String value);
}
