package org.andres_k.web.web.models.elements;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebElementRepository extends JpaRepository<WebElement, Long> {
    /**
     * Method findByName
     *
     * @param name the webElement name.
     * @return the webElement having the passed name or null if no webElement is found.
     */
    WebElement findByName(String name);
}
