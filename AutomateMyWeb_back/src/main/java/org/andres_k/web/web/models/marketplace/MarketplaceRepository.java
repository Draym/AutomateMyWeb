package org.andres_k.web.web.models.marketplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, Long> {
    /**
     * Method findByName
     *
     * @param name the marketplace name.
     * @return the marketplace having the passed name or null if no marketplace is found.
     */
    Marketplace findByName(String name);
}
