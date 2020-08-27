package com.mycloud.product.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycloud.product.domain.CharacteristicValue} entity.
 */
public class CharacteristicValueDTO implements Serializable {
    
    private Long id;

    private String defaultValue;

    private String values;

    private String valueForm;

    private String valueTo;

    private String rangeInterval;


    private Long characteristicId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getValueForm() {
        return valueForm;
    }

    public void setValueForm(String valueForm) {
        this.valueForm = valueForm;
    }

    public String getValueTo() {
        return valueTo;
    }

    public void setValueTo(String valueTo) {
        this.valueTo = valueTo;
    }

    public String getRangeInterval() {
        return rangeInterval;
    }

    public void setRangeInterval(String rangeInterval) {
        this.rangeInterval = rangeInterval;
    }

    public Long getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(Long characteristicId) {
        this.characteristicId = characteristicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CharacteristicValueDTO)) {
            return false;
        }

        return id != null && id.equals(((CharacteristicValueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CharacteristicValueDTO{" +
            "id=" + getId() +
            ", defaultValue='" + getDefaultValue() + "'" +
            ", values='" + getValues() + "'" +
            ", valueForm='" + getValueForm() + "'" +
            ", valueTo='" + getValueTo() + "'" +
            ", rangeInterval='" + getRangeInterval() + "'" +
            ", characteristicId=" + getCharacteristicId() +
            "}";
    }
}
