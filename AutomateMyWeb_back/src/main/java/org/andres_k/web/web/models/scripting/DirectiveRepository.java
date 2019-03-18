package org.andres_k.web.web.models.scripting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectiveRepository extends JpaRepository<Directive, Long> {
    /**
     * Method findByName
     *
     * @param name the script name.
     * @return the directive having the passed name or null if no directive is found.
     */
    Directive findByName(String name);
}
