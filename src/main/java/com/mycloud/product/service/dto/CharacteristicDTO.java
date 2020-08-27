package com.mycloud.product.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycloud.product.domain.Characteristic} entity.
 */
public class CharacteristicDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    private String decs;

    private Boolean uniqued;

    private String valueType;

    private Integer minCardinality;

    private Integer maxCardinality;

    private Boolean extensible;


    private Long specificationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public Boolean isUniqued() {
        return uniqued;
    }

    public void setUniqued(Boolean uniqued) {
        this.uniqued = uniqued;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Integer getMinCardinality() {
        return minCardinality;
    }

    public void setMinCardinality(Integer minCardinality) {
        this.minCardinality = minCardinality;
    }

    public Integer getMaxCardinality() {
        return maxCardinality;
    }

    public void setMaxCardinality(Integer maxCardinality) {
        this.maxCardinality = maxCardinality;
    }

    public Boolean isExtensible() {
        return extensible;
    }

    public void setExtensible(Boolean extensible) {
        this.extensible = extensible;
    }

    public Long getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CharacteristicDTO)) {
            return false;
        }

        return id != null && id.equals(((CharacteristicDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CharacteristicDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", decs='" + getDecs() + "'" +
            ", uniqued='" + isUniqued() + "'" +
            ", valueType='" + getValueType() + "'" +
            ", minCardinality=" + getMinCardinality() +
            ", maxCardinality=" + getMaxCardinality() +
            ", extensible='" + isExtensible() + "'" +
            ", specificationId=" + getSpecificationId() +
            "}";
    }
}
