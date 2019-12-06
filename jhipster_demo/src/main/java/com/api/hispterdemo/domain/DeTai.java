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
 * A DeTai.
 */
@Entity
@Table(name = "de_tai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeTai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ma_de_tai", nullable = false)
    private String maDeTai;

    @NotNull
    @Column(name = "ten_de_tai", nullable = false)
    private String tenDeTai;

    @NotNull
    @Column(name = "muc_tieu", nullable = false)
    private String mucTieu;

    @NotNull
    @Column(name = "ngay_bd_du_kien", nullable = false)
    private LocalDate ngayBDDuKien;

    @NotNull
    @Column(name = "ngay_kt_du_kien", nullable = false)
    private LocalDate ngayKTDuKien;

    @NotNull
    @Column(name = "kinh_phi_du_kien", nullable = false)
    private Double kinhPhiDuKien;

    @NotNull
    @Column(name = "noi_dung_tong_quan", nullable = false)
    private String noiDungTongQuan;

    @Column(name = "kinh_phi_hoan_thanh")
    private Double kinhPhiHoanThanh;

    @Column(name = "tong_diem")
    private Double tongDiem;

    @OneToMany(mappedBy = "deTai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DuToan> duToans = new HashSet<>();

    @OneToMany(mappedBy = "deTai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TienDo> tienDos = new HashSet<>();

    @OneToMany(mappedBy = "deTai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DanhGia> danhGias = new HashSet<>();

    @OneToMany(mappedBy = "deTai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FileAttach> fileAttaches = new HashSet<>();

    @OneToMany(mappedBy = "deTai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NhanSuTG> nhanSuTGS = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("deTais")
    private Officer officer;

    @ManyToOne
    @JsonIgnoreProperties("deTais")
    private TrangThai trangThai;

    @ManyToOne
    @JsonIgnoreProperties("deTais")
    private CapDeTai capDeTai;

    @ManyToOne
    @JsonIgnoreProperties("deTais")
    private XepLoai xepLoai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDeTai() {
        return maDeTai;
    }

    public DeTai maDeTai(String maDeTai) {
        this.maDeTai = maDeTai;
        return this;
    }

    public void setMaDeTai(String maDeTai) {
        this.maDeTai = maDeTai;
    }

    public String getTenDeTai() {
        return tenDeTai;
    }

    public DeTai tenDeTai(String tenDeTai) {
        this.tenDeTai = tenDeTai;
        return this;
    }

    public void setTenDeTai(String tenDeTai) {
        this.tenDeTai = tenDeTai;
    }

    public String getMucTieu() {
        return mucTieu;
    }

    public DeTai mucTieu(String mucTieu) {
        this.mucTieu = mucTieu;
        return this;
    }

    public void setMucTieu(String mucTieu) {
        this.mucTieu = mucTieu;
    }

    public LocalDate getNgayBDDuKien() {
        return ngayBDDuKien;
    }

    public DeTai ngayBDDuKien(LocalDate ngayBDDuKien) {
        this.ngayBDDuKien = ngayBDDuKien;
        return this;
    }

    public void setNgayBDDuKien(LocalDate ngayBDDuKien) {
        this.ngayBDDuKien = ngayBDDuKien;
    }

    public LocalDate getNgayKTDuKien() {
        return ngayKTDuKien;
    }

    public DeTai ngayKTDuKien(LocalDate ngayKTDuKien) {
        this.ngayKTDuKien = ngayKTDuKien;
        return this;
    }

    public void setNgayKTDuKien(LocalDate ngayKTDuKien) {
        this.ngayKTDuKien = ngayKTDuKien;
    }

    public Double getKinhPhiDuKien() {
        return kinhPhiDuKien;
    }

    public DeTai kinhPhiDuKien(Double kinhPhiDuKien) {
        this.kinhPhiDuKien = kinhPhiDuKien;
        return this;
    }

    public void setKinhPhiDuKien(Double kinhPhiDuKien) {
        this.kinhPhiDuKien = kinhPhiDuKien;
    }

    public String getNoiDungTongQuan() {
        return noiDungTongQuan;
    }

    public DeTai noiDungTongQuan(String noiDungTongQuan) {
        this.noiDungTongQuan = noiDungTongQuan;
        return this;
    }

    public void setNoiDungTongQuan(String noiDungTongQuan) {
        this.noiDungTongQuan = noiDungTongQuan;
    }

    public Double getKinhPhiHoanThanh() {
        return kinhPhiHoanThanh;
    }

    public DeTai kinhPhiHoanThanh(Double kinhPhiHoanThanh) {
        this.kinhPhiHoanThanh = kinhPhiHoanThanh;
        return this;
    }

    public void setKinhPhiHoanThanh(Double kinhPhiHoanThanh) {
        this.kinhPhiHoanThanh = kinhPhiHoanThanh;
    }

    public Double getTongDiem() {
        return tongDiem;
    }

    public DeTai tongDiem(Double tongDiem) {
        this.tongDiem = tongDiem;
        return this;
    }

    public void setTongDiem(Double tongDiem) {
        this.tongDiem = tongDiem;
    }

    public Set<DuToan> getDuToans() {
        return duToans;
    }

    public DeTai duToans(Set<DuToan> duToans) {
        this.duToans = duToans;
        return this;
    }

    public DeTai addDuToan(DuToan duToan) {
        this.duToans.add(duToan);
        duToan.setDeTai(this);
        return this;
    }

    public DeTai removeDuToan(DuToan duToan) {
        this.duToans.remove(duToan);
        duToan.setDeTai(null);
        return this;
    }

    public void setDuToans(Set<DuToan> duToans) {
        this.duToans = duToans;
    }

    public Set<TienDo> getTienDos() {
        return tienDos;
    }

    public DeTai tienDos(Set<TienDo> tienDos) {
        this.tienDos = tienDos;
        return this;
    }

    public DeTai addTienDo(TienDo tienDo) {
        this.tienDos.add(tienDo);
        tienDo.setDeTai(this);
        return this;
    }

    public DeTai removeTienDo(TienDo tienDo) {
        this.tienDos.remove(tienDo);
        tienDo.setDeTai(null);
        return this;
    }

    public void setTienDos(Set<TienDo> tienDos) {
        this.tienDos = tienDos;
    }

    public Set<DanhGia> getDanhGias() {
        return danhGias;
    }

    public DeTai danhGias(Set<DanhGia> danhGias) {
        this.danhGias = danhGias;
        return this;
    }

    public DeTai addDanhGia(DanhGia danhGia) {
        this.danhGias.add(danhGia);
        danhGia.setDeTai(this);
        return this;
    }

    public DeTai removeDanhGia(DanhGia danhGia) {
        this.danhGias.remove(danhGia);
        danhGia.setDeTai(null);
        return this;
    }

    public void setDanhGias(Set<DanhGia> danhGias) {
        this.danhGias = danhGias;
    }

    public Set<FileAttach> getFileAttaches() {
        return fileAttaches;
    }

    public DeTai fileAttaches(Set<FileAttach> fileAttaches) {
        this.fileAttaches = fileAttaches;
        return this;
    }

    public DeTai addFileAttach(FileAttach fileAttach) {
        this.fileAttaches.add(fileAttach);
        fileAttach.setDeTai(this);
        return this;
    }

    public DeTai removeFileAttach(FileAttach fileAttach) {
        this.fileAttaches.remove(fileAttach);
        fileAttach.setDeTai(null);
        return this;
    }

    public void setFileAttaches(Set<FileAttach> fileAttaches) {
        this.fileAttaches = fileAttaches;
    }

    public Set<NhanSuTG> getNhanSuTGS() {
        return nhanSuTGS;
    }

    public DeTai nhanSuTGS(Set<NhanSuTG> nhanSuTGS) {
        this.nhanSuTGS = nhanSuTGS;
        return this;
    }

    public DeTai addNhanSuTG(NhanSuTG nhanSuTG) {
        this.nhanSuTGS.add(nhanSuTG);
        nhanSuTG.setDeTai(this);
        return this;
    }

    public DeTai removeNhanSuTG(NhanSuTG nhanSuTG) {
        this.nhanSuTGS.remove(nhanSuTG);
        nhanSuTG.setDeTai(null);
        return this;
    }

    public void setNhanSuTGS(Set<NhanSuTG> nhanSuTGS) {
        this.nhanSuTGS = nhanSuTGS;
    }

    public Officer getOfficer() {
        return officer;
    }

    public DeTai officer(Officer officer) {
        this.officer = officer;
        return this;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public TrangThai getTrangThai() {
        return trangThai;
    }

    public DeTai trangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
        return this;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }

    public CapDeTai getCapDeTai() {
        return capDeTai;
    }

    public DeTai capDeTai(CapDeTai capDeTai) {
        this.capDeTai = capDeTai;
        return this;
    }

    public void setCapDeTai(CapDeTai capDeTai) {
        this.capDeTai = capDeTai;
    }

    public XepLoai getXepLoai() {
        return xepLoai;
    }

    public DeTai xepLoai(XepLoai xepLoai) {
        this.xepLoai = xepLoai;
        return this;
    }

    public void setXepLoai(XepLoai xepLoai) {
        this.xepLoai = xepLoai;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeTai)) {
            return false;
        }
        return id != null && id.equals(((DeTai) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DeTai{" +
            "id=" + getId() +
            ", maDeTai='" + getMaDeTai() + "'" +
            ", tenDeTai='" + getTenDeTai() + "'" +
            ", mucTieu='" + getMucTieu() + "'" +
            ", ngayBDDuKien='" + getNgayBDDuKien() + "'" +
            ", ngayKTDuKien='" + getNgayKTDuKien() + "'" +
            ", kinhPhiDuKien=" + getKinhPhiDuKien() +
            ", noiDungTongQuan='" + getNoiDungTongQuan() + "'" +
            ", kinhPhiHoanThanh=" + getKinhPhiHoanThanh() +
            ", tongDiem=" + getTongDiem() +
            "}";
    }
}
