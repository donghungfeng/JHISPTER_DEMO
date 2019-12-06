package com.api.hispterdemo.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A TienDo.
 */
@Entity
@Table(name = "tien_do")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TienDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "tien_do_hoan_thanh", nullable = false)
    private Integer tienDoHoanThanh;

    @NotNull
    @Column(name = "mo_ta", nullable = false)
    private String moTa;

    @NotNull
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDate ngayCapNhat;

    @OneToMany(mappedBy = "tienDo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FileAttach> fileAttaches = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("tienDos")
    private DeTai deTai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTienDoHoanThanh() {
        return tienDoHoanThanh;
    }

    public TienDo tienDoHoanThanh(Integer tienDoHoanThanh) {
        this.tienDoHoanThanh = tienDoHoanThanh;
        return this;
    }

    public void setTienDoHoanThanh(Integer tienDoHoanThanh) {
        this.tienDoHoanThanh = tienDoHoanThanh;
    }

    public String getMoTa() {
        return moTa;
    }

    public TienDo moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayCapNhat() {
        return ngayCapNhat;
    }

    public TienDo ngayCapNhat(LocalDate ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
        return this;
    }

    public void setNgayCapNhat(LocalDate ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public Set<FileAttach> getFileAttaches() {
        return fileAttaches;
    }

    public TienDo fileAttaches(Set<FileAttach> fileAttaches) {
        this.fileAttaches = fileAttaches;
        return this;
    }

    public TienDo addFileAttach(FileAttach fileAttach) {
        this.fileAttaches.add(fileAttach);
        fileAttach.setTienDo(this);
        return this;
    }

    public TienDo removeFileAttach(FileAttach fileAttach) {
        this.fileAttaches.remove(fileAttach);
        fileAttach.setTienDo(null);
        return this;
    }

    public void setFileAttaches(Set<FileAttach> fileAttaches) {
        this.fileAttaches = fileAttaches;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public TienDo deTai(DeTai deTai) {
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
        if (!(o instanceof TienDo)) {
            return false;
        }
        return id != null && id.equals(((TienDo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TienDo{" +
            "id=" + getId() +
            ", tienDoHoanThanh=" + getTienDoHoanThanh() +
            ", moTa='" + getMoTa() + "'" +
            ", ngayCapNhat='" + getNgayCapNhat() + "'" +
            "}";
    }
}
