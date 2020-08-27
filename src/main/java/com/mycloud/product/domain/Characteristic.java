package com.mycloud.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Characteristic.
 */
@Entity
@Table(name = "characteristic")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Characteristic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "decs")
    private String decs;

    @Column(name = "uniqued")
    private Boolean uniqued;

    @Column(name = "value_type")
    private String valueType;

    @Column(name = "min_cardinality")
    private Integer minCardinality;

    @Column(name = "max_cardinality")
    private Integer maxCardinality;

    @Column(name = "extensible")
    private Boolean extensible;

    @OneToMany(mappedBy = "characteristic")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CharacteristicValue> values = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "characteristices", allowSetters = true)
    private Specification specification;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Characteristic name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecs() {
        return decs;
    }

    public Characteristic decs(String decs) {
        this.decs = decs;
        return this;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public Boolean isUniqued() {
        return uniqued;
    }

    public Characteristic uniqued(Boolean uniqued) {
        this.uniqued = uniqued;
        return this;
    }

    public void setUniqued(Boolean uniqued) {
        this.uniqued = uniqued;
    }

    public String getValueType() {
        return valueType;
    }

    public Characteristic valueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Integer getMinCardinality() {
        return minCardinality;
    }

    public Characteristic minCardinality(Integer minCardinality) {
        this.minCardinality = minCardinality;
        return this;
    }

    public void setMinCardinality(Integer minCardinality) {
        this.minCardinality = minCardinality;
    }

    public Integer getMaxCardinality() {
        return maxCardinality;
    }

    public Characteristic maxCardinality(Integer maxCardinality) {
        this.maxCardinality = maxCardinality;
        return this;
    }

    public void setMaxCardinality(Integer maxCardinality) {
        this.maxCardinality = maxCardinality;
    }

    public Boolean isExtensible() {
        return extensible;
    }

    public Characteristic extensible(Boolean extensible) {
        this.extensible = extensible;
        return this;
    }

    public void setExtensible(Boolean extensible) {
        this.extensible = extensible;
    }

    public Set<CharacteristicValue> getValues() {
        return values;
    }

    public Characteristic values(Set<CharacteristicValue> characteristicValues) {
        this.values = characteristicValues;
        return this;
    }

    public Characteristic addValues(CharacteristicValue characteristicValue) {
        this.values.add(characteristicValue);
        characteristicValue.setCharacteristic(this);
        return this;
    }

    public Characteristic removeValues(CharacteristicValue characteristicValue) {
        this.values.remove(characteristicValue);
        characteristicValue.setCharacteristic(null);
        return this;
    }

    public void setValues(Set<CharacteristicValue> characteristicValues) {
        this.values = characteristicValues;
    }

    public Specification getSpecification() {
        return specification;
    }

    public Characteristic specification(Specification specification) {
        this.specification = specification;
        return this;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Characteristic)) {
            return false;
        }
        return id != null && id.equals(((Characteristic) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Characteristic{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", decs='" + getDecs() + "'" +
            ", uniqued='" + isUniqued() + "'" +
            ", valueType='" + getValueType() + "'" +
            ", minCardinality=" + getMinCardinality() +
            ", maxCardinality=" + getMaxCardinality() +
            ", extensible='" + isExtensible() + "'" +
            "}";
    }
}
