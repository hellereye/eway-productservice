package com.mycloud.product.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Specification.
 */
@Entity
@Table(name = "specification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Specification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "jhi_desc")
    private String desc;

    @OneToMany(mappedBy = "specification")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Characteristic> characteristices = new HashSet<>();

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

    public Specification name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public Specification desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Characteristic> getCharacteristices() {
        return characteristices;
    }

    public Specification characteristices(Set<Characteristic> characteristics) {
        this.characteristices = characteristics;
        return this;
    }

    public Specification addCharacteristices(Characteristic characteristic) {
        this.characteristices.add(characteristic);
        characteristic.setSpecification(this);
        return this;
    }

    public Specification removeCharacteristices(Characteristic characteristic) {
        this.characteristices.remove(characteristic);
        characteristic.setSpecification(null);
        return this;
    }

    public void setCharacteristices(Set<Characteristic> characteristics) {
        this.characteristices = characteristics;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Specification)) {
            return false;
        }
        return id != null && id.equals(((Specification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Specification{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
