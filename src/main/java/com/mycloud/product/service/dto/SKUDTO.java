package com.mycloud.product.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.mycloud.product.domain.SKU} entity.
 */
public class SKUDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String code;

    private String barCode;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer stockQuantiry;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantiry() {
        return stockQuantiry;
    }

    public void setStockQuantiry(Integer stockQuantiry) {
        this.stockQuantiry = stockQuantiry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SKUDTO)) {
            return false;
        }

        return id != null && id.equals(((SKUDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SKUDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", barCode='" + getBarCode() + "'" +
            ", price=" + getPrice() +
            ", stockQuantiry=" + getStockQuantiry() +
            "}";
    }
}
