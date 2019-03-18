package org.andres_k.web.web.models.elements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance
@DiscriminatorColumn(name = "webelement_type")
@Table(name = "webelements")
public class WebElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String cssSelector;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCssSelector() {
        return this.cssSelector;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }
}
