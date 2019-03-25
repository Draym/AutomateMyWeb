package org.andres_k.web.backend.models.items.elements;

import org.andres_k.web.backend.models.UserPropertyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebElementRepository extends UserPropertyRepository<WebElement> {

}
