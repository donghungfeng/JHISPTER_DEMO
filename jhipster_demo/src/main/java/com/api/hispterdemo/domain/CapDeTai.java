package com.api.hispterdemo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CapDeTai.
 */
@Entity
@Table(name = "cap_de_tai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CapDeTai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ten_cap_de_tai", nullable = false)
    private String tenCapDeTai;

    @OneToMany(mappedBy = "capDeTai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DeTai> deTais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenCapDeTai() {
        return tenCapDeTai;
    }

    public CapDeTai tenCapDeTai(String tenCapDeTai) {
        this.tenCapDeTai = tenCapDeTai;
        return this;
    }

    public void setTenCapDeTai(String tenCapDeTai) {
        this.tenCapDeTai = tenCapDeTai;
    }

    public Set<DeTai> getDeTais() {
        return deTais;
    }

    public CapDeTai deTais(Set<DeTai> deTais) {
        this.deTais = deTais;
        return this;
    }

    public CapDeTai addDeTai(DeTai deTai) {
        this.deTais.add(deTai);
        deTai.setCapDeTai(this);
        return this;
    }

    public CapDeTai removeDeTai(DeTai deTai) {
        this.deTais.remove(deTai);
        deTai.setCapDeTai(null);
        return this;
    }

    public void setDeTais(Set<DeTai> deTais) {
        this.deTais = deTais;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CapDeTai)) {
            return false;
        }
        return id != null && id.equals(((CapDeTai) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CapDeTai{" +
            "id=" + getId() +
            ", tenCapDeTai='" + getTenCapDeTai() + "'" +
            "}";
    }
}
