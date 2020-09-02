package com.mycloud.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedEntityGraph(name = "Category.findByParent",
                attributeNodes = {@NamedAttributeNode(value = "children", subgraph = "son")}, //第一层
        subgraphs = {@NamedSubgraph(name = "son", attributeNodes = @NamedAttributeNode(value = "children", subgraph = "grandson")), //第二层
                @NamedSubgraph(name = "grandson", attributeNodes = @NamedAttributeNode(value = "children"))//第三层
        })
@NamedEntityGraph(name = "category.children", attributeNodes = @NamedAttributeNode("children"))

@EntityListeners(value = AuditingEntityListener.class)
public class Category extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Product> products = new HashSet<>();

    @OrderColumn
    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL, fetch=FetchType.EAGER ,orphanRemoval=true)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Category> children = new HashSet<>();

    @ManyToOne()
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    private Category parent;

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

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Category description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Category products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public Category addProducts(Product product) {
        this.products.add(product);
        product.setCategory(this);
        return this;
    }

    public Category removeProducts(Product product) {
        this.products.remove(product);
        product.setCategory(null);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public Category children(Set<Category> categories) {
        this.children = categories;
        return this;
    }

    public Category addChildren(Category category) {
        this.children.add(category);
        category.setParent(this);
        return this;
    }

    public Category removeChildren(Category category) {
        this.children.remove(category);
        category.setParent(null);
        return this;
    }

    public void setChildren(Set<Category> categories) {
        this.children = categories;
    }

    public Category getParent() {
        return parent;
    }

    public Category parent(Category category) {
        this.parent = category;
        return this;
    }

    public void setParent(Category category) {
        this.parent = category;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
