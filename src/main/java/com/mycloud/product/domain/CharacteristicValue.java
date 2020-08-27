package com.mycloud.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CharacteristicValue.
 */
@Entity
@Table(name = "characteristic_value")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CharacteristicValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "jhi_values")
    private String values;

    @Column(name = "value_form")
    private String valueForm;

    @Column(name = "value_to")
    private String valueTo;

    @Column(name = "range_interval")
    private String rangeInterval;

    @ManyToOne
    @JsonIgnoreProperties(value = "values", allowSetters = true)
    private Characteristic characteristic;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public CharacteristicValue defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValues() {
        return values;
    }

    public CharacteristicValue values(String values) {
        this.values = values;
        return this;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getValueForm() {
        return valueForm;
    }

    public CharacteristicValue valueForm(String valueForm) {
        this.valueForm = valueForm;
        return this;
    }

    public void setValueForm(String valueForm) {
        this.valueForm = valueForm;
    }

    public String getValueTo() {
        return valueTo;
    }

    public CharacteristicValue valueTo(String valueTo) {
        this.valueTo = valueTo;
        return this;
    }

    public void setValueTo(String valueTo) {
        this.valueTo = valueTo;
    }

    public String getRangeInterval() {
        return rangeInterval;
    }

    public CharacteristicValue rangeInterval(String rangeInterval) {
        this.rangeInterval = rangeInterval;
        return this;
    }

    public void setRangeInterval(String rangeInterval) {
        this.rangeInterval = rangeInterval;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public CharacteristicValue characteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
        return this;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CharacteristicValue)) {
            return false;
        }
        return id != null && id.equals(((CharacteristicValue) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CharacteristicValue{" +
            "id=" + getId() +
            ", defaultValue='" + getDefaultValue() + "'" +
            ", values='" + getValues() + "'" +
            ", valueForm='" + getValueForm() + "'" +
            ", valueTo='" + getValueTo() + "'" +
            ", rangeInterval='" + getRangeInterval() + "'" +
            "}";
    }
}
