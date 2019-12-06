package com.api.hispterdemo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TrangThai.
 */
@Entity
@Table(name = "trang_thai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TrangThai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ten_trang_thai", nullable = false)
    private String tenTrangThai;

    @OneToMany(mappedBy = "trangThai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DeTai> deTais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public TrangThai tenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
        return this;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public Set<DeTai> getDeTais() {
        return deTais;
    }

    public TrangThai deTais(Set<DeTai> deTais) {
        this.deTais = deTais;
        return this;
    }

    public TrangThai addDeTai(DeTai deTai) {
        this.deTais.add(deTai);
        deTai.setTrangThai(this);
        return this;
    }

    public TrangThai removeDeTai(DeTai deTai) {
        this.deTais.remove(deTai);
        deTai.setTrangThai(null);
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
        if (!(o instanceof TrangThai)) {
            return false;
        }
        return id != null && id.equals(((TrangThai) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TrangThai{" +
            "id=" + getId() +
            ", tenTrangThai='" + getTenTrangThai() + "'" +
            "}";
    }
}
