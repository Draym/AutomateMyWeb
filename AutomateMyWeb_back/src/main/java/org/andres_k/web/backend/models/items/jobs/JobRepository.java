package org.andres_k.web.backend.models.items.jobs;

import org.andres_k.web.backend.models.UserProperty;
import org.andres_k.web.backend.models.UserPropertyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository<T extends UserProperty> extends UserPropertyRepository<T> {
}
