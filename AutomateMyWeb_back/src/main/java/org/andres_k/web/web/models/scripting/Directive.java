package org.andres_k.web.web.models.scripting;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance
@DiscriminatorColumn(name = "directive_type")
@Table(name = "directives")
public class Directive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Id
    private long ownerId;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String value;

    /**
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * @return the ownerId
     */
    public long getOwnerId() {
        return this.ownerId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
}
