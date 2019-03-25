package org.andres_k.web.backend.models.items.templates;

import org.andres_k.web.backend.models.UserPropertyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateElementRepository extends UserPropertyRepository<Template> {

}
