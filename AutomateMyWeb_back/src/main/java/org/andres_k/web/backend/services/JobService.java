package org.andres_k.web.backend.services;

import org.andres_k.web.backend.models.UserProperty;
import org.andres_k.web.backend.models.items.jobs.JobRepository;
import org.andres_k.web.backend.models.items.jobs.Script;
import org.andres_k.web.backend.utils.EncryptTools;
import org.andres_k.web.backend.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Service to manage Script and Directive entities
 *
 * @param <T> should be jobs/Script or jobs/Directive
 */
@Service
public class JobService<T extends UserProperty> {

    @Autowired
    JobRepository<T> jobRepository;

    public String createSharedLink(Script script) throws PasswordStorage.CannotPerformOperationException {
        return EncryptTools.encrypt("name=" + script.getName() + "_id=" + script.getId());
    }

    public T getJobFromLink(String link) throws Exception {
        String[] values = EncryptTools.decrypt(link).split("_");

        if (values.length == 2) {
            String id = values[1];

            Optional<T> job = this.jobRepository.findById(Long.valueOf(id));

            if (!job.isPresent())
                throw new EntityNotFoundException("The shared link is invalid: script doesn't exist");
            return job.get();
        } else {
            throw new Exception("The shared link is invalid.");
        }
    }

    public T find(Long jobId, Long userId) {
        T job = this.jobRepository.getByIdAndUserId(jobId, userId);

        if (job == null)
            throw new EntityNotFoundException("The job has not been found in the user' scripts");
        return job;
    }
}
