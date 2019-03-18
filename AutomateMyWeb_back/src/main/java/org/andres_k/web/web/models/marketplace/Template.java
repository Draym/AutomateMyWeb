package org.andres_k.web.web.models.marketplace;

import org.andres_k.web.web.models.elements.WebElement;
import org.andres_k.web.web.models.scripting.Directive;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance
@DiscriminatorColumn(name = "template_type")
@Table(name = "templates")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    @ElementCollection
    private List<WebElement> elements = new ArrayList<>();
    @NotNull
    @ElementCollection
    private List<Directive> directives = new ArrayList<>();

    /**
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the elements
     */
    public List<WebElement> getElements() {
        return this.elements;
    }

    /**
     * @return the directives
     */
    public List<Directive> getDirectives() {
        return this.directives;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param elements the elements to set
     */
    public void setScriptIds(List<WebElement> elements) {
        this.elements = elements;
    }
    /**
     * @param directives the directives to set
     */
    public void setDirectives(List<Directive> directives) {
        this.directives = directives;
    }
}
