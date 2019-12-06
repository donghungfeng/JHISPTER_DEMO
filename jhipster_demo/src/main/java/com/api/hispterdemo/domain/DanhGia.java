package com.api.hispterdemo.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DanhGia.
 */
@Entity
@Table(name = "danh_gia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DanhGia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "loai_danh_gia", nullable = false)
    private Integer loaiDanhGia;

    @NotNull
    @Column(name = "diem_danh_gia", nullable = false)
    private Integer diemDanhGia;

    @ManyToOne
    @JsonIgnoreProperties("danhGias")
    private DeTai deTai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoaiDanhGia() {
        return loaiDanhGia;
    }

    public DanhGia loaiDanhGia(Integer loaiDanhGia) {
        this.loaiDanhGia = loaiDanhGia;
        return this;
    }

    public void setLoaiDanhGia(Integer loaiDanhGia) {
        this.loaiDanhGia = loaiDanhGia;
    }

    public Integer getDiemDanhGia() {
        return diemDanhGia;
    }

    public DanhGia diemDanhGia(Integer diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
        return this;
    }

    public void setDiemDanhGia(Integer diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public DanhGia deTai(DeTai deTai) {
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
        if (!(o instanceof DanhGia)) {
            return false;
        }
        return id != null && id.equals(((DanhGia) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DanhGia{" +
            "id=" + getId() +
            ", loaiDanhGia=" + getLoaiDanhGia() +
            ", diemDanhGia=" + getDiemDanhGia() +
            "}";
    }
}
