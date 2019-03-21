package org.andres_k.web.web.models.items.jobs;

import org.andres_k.web.web.models.UserProperty;
import org.andres_k.web.web.models.UserPropertyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository<T extends UserProperty> extends UserPropertyRepository<T> {
}
