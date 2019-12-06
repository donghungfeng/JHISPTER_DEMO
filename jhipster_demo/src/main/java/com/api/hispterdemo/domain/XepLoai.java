package com.api.hispterdemo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A XepLoai.
 */
@Entity
@Table(name = "xep_loai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class XepLoai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ten_xep_loai", nullable = false)
    private String tenXepLoai;

    @OneToMany(mappedBy = "xepLoai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DeTai> deTais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenXepLoai() {
        return tenXepLoai;
    }

    public XepLoai tenXepLoai(String tenXepLoai) {
        this.tenXepLoai = tenXepLoai;
        return this;
    }

    public void setTenXepLoai(String tenXepLoai) {
        this.tenXepLoai = tenXepLoai;
    }

    public Set<DeTai> getDeTais() {
        return deTais;
    }

    public XepLoai deTais(Set<DeTai> deTais) {
        this.deTais = deTais;
        return this;
    }

    public XepLoai addDeTai(DeTai deTai) {
        this.deTais.add(deTai);
        deTai.setXepLoai(this);
        return this;
    }

    public XepLoai removeDeTai(DeTai deTai) {
        this.deTais.remove(deTai);
        deTai.setXepLoai(null);
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
        if (!(o instanceof XepLoai)) {
            return false;
        }
        return id != null && id.equals(((XepLoai) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "XepLoai{" +
            "id=" + getId() +
            ", tenXepLoai='" + getTenXepLoai() + "'" +
            "}";
    }
}
