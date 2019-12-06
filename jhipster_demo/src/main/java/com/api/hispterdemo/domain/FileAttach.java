package com.api.hispterdemo.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A FileAttach.
 */
@Entity
@Table(name = "file_attach")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FileAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "mo_ta", nullable = false)
    private String moTa;

    
    @Lob
    @Column(name = "noi_dung", nullable = false)
    private byte[] noiDung;

    @Column(name = "noi_dung_content_type", nullable = false)
    private String noiDungContentType;

    @NotNull
    @Column(name = "ngay_cap_nhat", nullable = false)
    private LocalDate ngayCapNhat;

    @ManyToOne
    @JsonIgnoreProperties("fileAttaches")
    private DeTai deTai;

    @ManyToOne
    @JsonIgnoreProperties("fileAttaches")
    private TienDo tienDo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoTa() {
        return moTa;
    }

    public FileAttach moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public byte[] getNoiDung() {
        return noiDung;
    }

    public FileAttach noiDung(byte[] noiDung) {
        this.noiDung = noiDung;
        return this;
    }

    public void setNoiDung(byte[] noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiDungContentType() {
        return noiDungContentType;
    }

    public FileAttach noiDungContentType(String noiDungContentType) {
        this.noiDungContentType = noiDungContentType;
        return this;
    }

    public void setNoiDungContentType(String noiDungContentType) {
        this.noiDungContentType = noiDungContentType;
    }

    public LocalDate getNgayCapNhat() {
        return ngayCapNhat;
    }

    public FileAttach ngayCapNhat(LocalDate ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
        return this;
    }

    public void setNgayCapNhat(LocalDate ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public FileAttach deTai(DeTai deTai) {
        this.deTai = deTai;
        return this;
    }

    public void setDeTai(DeTai deTai) {
        this.deTai = deTai;
    }

    public TienDo getTienDo() {
        return tienDo;
    }

    public FileAttach tienDo(TienDo tienDo) {
        this.tienDo = tienDo;
        return this;
    }

    public void setTienDo(TienDo tienDo) {
        this.tienDo = tienDo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileAttach)) {
            return false;
        }
        return id != null && id.equals(((FileAttach) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FileAttach{" +
            "id=" + getId() +
            ", moTa='" + getMoTa() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", noiDungContentType='" + getNoiDungContentType() + "'" +
            ", ngayCapNhat='" + getNgayCapNhat() + "'" +
            "}";
    }
}
