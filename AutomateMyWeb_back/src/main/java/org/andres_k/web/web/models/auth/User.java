package org.andres_k.web.web.models.auth;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance
@DiscriminatorColumn(name = "user_type")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String email;
    @NotNull
    private String pseudo;
    @NotNull
    private String password;
    @ElementCollection
    private List<String> scriptIds = new ArrayList<>();

    /**
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * @return the scriptIds
     */
    public List<String> getScriptIds() {
        return this.scriptIds;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @param scriptIds the scriptIds to set
     */
    public void setScriptIds(List<String> scriptIds) {
        this.scriptIds = scriptIds;
    }
}
