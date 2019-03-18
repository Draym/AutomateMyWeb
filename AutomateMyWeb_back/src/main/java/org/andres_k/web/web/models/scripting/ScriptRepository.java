package org.andres_k.web.web.models.scripting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {
    /**
     * Method findByName
     *
     * @param name the script name.
     * @return the script having the passed name or null if no script is found.
     */
    Script findByName(String name);
}
