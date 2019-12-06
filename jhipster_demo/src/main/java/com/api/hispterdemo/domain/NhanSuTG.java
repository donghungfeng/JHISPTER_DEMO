package com.api.hispterdemo.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A NhanSuTG.
 */
@Entity
@Table(name = "nhan_su_tg")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NhanSuTG implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "vai_tro", nullable = false)
    private String vaiTro;

    @ManyToOne
    @JsonIgnoreProperties("nhanSuTGS")
    private Officer officer;

    @ManyToOne
    @JsonIgnoreProperties("nhanSuTGS")
    private DeTai deTai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public NhanSuTG vaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
        return this;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Officer getOfficer() {
        return officer;
    }

    public NhanSuTG officer(Officer officer) {
        this.officer = officer;
        return this;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public NhanSuTG deTai(DeTai deTai) {
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
        if (!(o instanceof NhanSuTG)) {
            return false;
        }
        return id != null && id.equals(((NhanSuTG) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NhanSuTG{" +
            "id=" + getId() +
            ", vaiTro='" + getVaiTro() + "'" +
            "}";
    }
}
