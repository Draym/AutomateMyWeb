package org.andres_k.web.web.models.marketplace;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name = "marketplace_type")
@Table(name = "marketplaces")
public class Marketplace {
}
