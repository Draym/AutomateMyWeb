package org.andres_k.web.web.models.marketplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    /**
     * Method findByName
     *
     * @param name the template name.
     * @return the template having the passed name or null if no template is found.
     */
    Template findByName(String name);
}
