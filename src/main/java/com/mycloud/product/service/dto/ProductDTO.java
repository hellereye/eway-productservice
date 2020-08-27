package com.mycloud.product.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Lob;
import com.mycloud.product.domain.enumeration.UnitOfMeasurement;
import com.mycloud.product.domain.enumeration.ProductStatus;

/**
 * A DTO for the {@link com.mycloud.product.domain.Product} entity.
 */
@ApiModel(description = "Product sold by the Online store")
public class ProductDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 2)
    private String name;

    private String brand;

    private String description;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal price;

    @Lob
    private byte[] image;

    private String imageContentType;
    private UnitOfMeasurement productUnit;

    private ProductStatus status;


    private Long categoryId;
    
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public UnitOfMeasurement getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(UnitOfMeasurement productUnit) {
        this.productUnit = productUnit;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", brand='" + getBrand() + "'" +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", image='" + getImage() + "'" +
            ", productUnit='" + getProductUnit() + "'" +
            ", status='" + getStatus() + "'" +
            ", categoryId=" + getCategoryId() +
            "}";
    }
}
