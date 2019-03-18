package org.andres_k.web.web.models.marketplace;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance
@DiscriminatorColumn(name = "mp_script_type")
@Table(name = "marketplace_scripts")
public class MarketplaceScript {
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
    private String imageURL;
    @NotNull
    private String value;
    @NotNull
    private Long version;

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
     * @return the imageURL
     */
    public String getImageURL() {
        return this.imageURL;
    }
    /**
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @return the version
     */
    public long getVersion() {
        return this.version;
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
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    /**
     * @param version the version to set
     */
    public void setVersion(long version) {
        this.version = version;
    }

}
