package org.andres_k.web.web.models.marketplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplaceScriptRepository extends JpaRepository<MarketplaceScript, Long> {
    /**
     * Method findByName
     *
     * @param name the script name.
     * @return the script having the passed name or null if no script is found.
     */
    MarketplaceScript findByName(String name);
}
