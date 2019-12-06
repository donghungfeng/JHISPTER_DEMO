package com.api.hispterdemo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ToChuc.
 */
@Entity
@Table(name = "to_chuc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ToChuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ten_to_chuc", nullable = false)
    private String tenToChuc;

    @OneToMany(mappedBy = "toChuc")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Officer> officers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenToChuc() {
        return tenToChuc;
    }

    public ToChuc tenToChuc(String tenToChuc) {
        this.tenToChuc = tenToChuc;
        return this;
    }

    public void setTenToChuc(String tenToChuc) {
        this.tenToChuc = tenToChuc;
    }

    public Set<Officer> getOfficers() {
        return officers;
    }

    public ToChuc officers(Set<Officer> officers) {
        this.officers = officers;
        return this;
    }

    public ToChuc addOfficer(Officer officer) {
        this.officers.add(officer);
        officer.setToChuc(this);
        return this;
    }

    public ToChuc removeOfficer(Officer officer) {
        this.officers.remove(officer);
        officer.setToChuc(null);
        return this;
    }

    public void setOfficers(Set<Officer> officers) {
        this.officers = officers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToChuc)) {
            return false;
        }
        return id != null && id.equals(((ToChuc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ToChuc{" +
            "id=" + getId() +
            ", tenToChuc='" + getTenToChuc() + "'" +
            "}";
    }
}
