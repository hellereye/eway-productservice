package com.mycloud.product.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A SKU.
 */
@Entity
@Table(name = "sku")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SKU implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "bar_code")
    private String barCode;

    @NotNull
    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "stock_quantiry", nullable = false)
    private Integer stockQuantiry;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public SKU code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarCode() {
        return barCode;
    }

    public SKU barCode(String barCode) {
        this.barCode = barCode;
        return this;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SKU price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantiry() {
        return stockQuantiry;
    }

    public SKU stockQuantiry(Integer stockQuantiry) {
        this.stockQuantiry = stockQuantiry;
        return this;
    }

    public void setStockQuantiry(Integer stockQuantiry) {
        this.stockQuantiry = stockQuantiry;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SKU)) {
            return false;
        }
        return id != null && id.equals(((SKU) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SKU{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", barCode='" + getBarCode() + "'" +
            ", price=" + getPrice() +
            ", stockQuantiry=" + getStockQuantiry() +
            "}";
    }
}
