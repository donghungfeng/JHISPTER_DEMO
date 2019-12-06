package com.api.hispterdemo.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DuToan.
 */
@Entity
@Table(name = "du_toan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DuToan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "loai_du_toan", nullable = false)
    private Integer loaiDuToan;

    @NotNull
    @Column(name = "du_toan", nullable = false)
    private Double duToan;

    @ManyToOne
    @JsonIgnoreProperties("duToans")
    private DeTai deTai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoaiDuToan() {
        return loaiDuToan;
    }

    public DuToan loaiDuToan(Integer loaiDuToan) {
        this.loaiDuToan = loaiDuToan;
        return this;
    }

    public void setLoaiDuToan(Integer loaiDuToan) {
        this.loaiDuToan = loaiDuToan;
    }

    public Double getDuToan() {
        return duToan;
    }

    public DuToan duToan(Double duToan) {
        this.duToan = duToan;
        return this;
    }

    public void setDuToan(Double duToan) {
        this.duToan = duToan;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public DuToan deTai(DeTai deTai) {
        this.deTai = deTai;
        return this;
    }

    public void setDeTai(DeTai deTai) {
        this.deTai = deTai;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DuToan)) {
            return false;
        }
        return id != null && id.equals(((DuToan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DuToan{" +
            "id=" + getId() +
            ", loaiDuToan=" + getLoaiDuToan() +
            ", duToan=" + getDuToan() +
            "}";
    }
}
