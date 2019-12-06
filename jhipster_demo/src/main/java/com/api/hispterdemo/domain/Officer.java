package com.api.hispterdemo.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Officer.
 */
@Entity
@Table(name = "officer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Officer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ten_nhan_su", nullable = false)
    private String tenNhanSu;

    @NotNull
    @Column(name = "dien_thoai", nullable = false)
    private String dienThoai;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "fax")
    private String fax;

    @NotNull
    @Column(name = "dia_chi", nullable = false)
    private String diaChi;

    @OneToMany(mappedBy = "officer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DeTai> deTais = new HashSet<>();

    @OneToMany(mappedBy = "officer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NhanSuTG> nhanSuTGS = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("officers")
    private ToChuc toChuc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNhanSu() {
        return tenNhanSu;
    }

    public Officer tenNhanSu(String tenNhanSu) {
        this.tenNhanSu = tenNhanSu;
        return this;
    }

    public void setTenNhanSu(String tenNhanSu) {
        this.tenNhanSu = tenNhanSu;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public Officer dienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
        return this;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public Officer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public Officer fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public Officer diaChi(String diaChi) {
        this.diaChi = diaChi;
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Set<DeTai> getDeTais() {
        return deTais;
    }

    public Officer deTais(Set<DeTai> deTais) {
        this.deTais = deTais;
        return this;
    }

    public Officer addDeTai(DeTai deTai) {
        this.deTais.add(deTai);
        deTai.setOfficer(this);
        return this;
    }

    public Officer removeDeTai(DeTai deTai) {
        this.deTais.remove(deTai);
        deTai.setOfficer(null);
        return this;
    }

    public void setDeTais(Set<DeTai> deTais) {
        this.deTais = deTais;
    }

    public Set<NhanSuTG> getNhanSuTGS() {
        return nhanSuTGS;
    }

    public Officer nhanSuTGS(Set<NhanSuTG> nhanSuTGS) {
        this.nhanSuTGS = nhanSuTGS;
        return this;
    }

    public Officer addNhanSuTG(NhanSuTG nhanSuTG) {
        this.nhanSuTGS.add(nhanSuTG);
        nhanSuTG.setOfficer(this);
        return this;
    }

    public Officer removeNhanSuTG(NhanSuTG nhanSuTG) {
        this.nhanSuTGS.remove(nhanSuTG);
        nhanSuTG.setOfficer(null);
        return this;
    }

    public void setNhanSuTGS(Set<NhanSuTG> nhanSuTGS) {
        this.nhanSuTGS = nhanSuTGS;
    }

    public ToChuc getToChuc() {
        return toChuc;
    }

    public Officer toChuc(ToChuc toChuc) {
        this.toChuc = toChuc;
        return this;
    }

    public void setToChuc(ToChuc toChuc) {
        this.toChuc = toChuc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Officer)) {
            return false;
        }
        return id != null && id.equals(((Officer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Officer{" +
            "id=" + getId() +
            ", tenNhanSu='" + getTenNhanSu() + "'" +
            ", dienThoai='" + getDienThoai() + "'" +
            ", email='" + getEmail() + "'" +
            ", fax='" + getFax() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            "}";
    }
}
