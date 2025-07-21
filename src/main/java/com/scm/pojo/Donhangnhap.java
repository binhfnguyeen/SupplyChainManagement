/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "donhangnhap")
@NamedQueries({
    @NamedQuery(name = "Donhangnhap.findAll", query = "SELECT d FROM Donhangnhap d"),
    @NamedQuery(name = "Donhangnhap.findById", query = "SELECT d FROM Donhangnhap d WHERE d.id = :id"),
    @NamedQuery(name = "Donhangnhap.findByTongTien", query = "SELECT d FROM Donhangnhap d WHERE d.tongTien = :tongTien"),
    @NamedQuery(name = "Donhangnhap.findByTinhTrang", query = "SELECT d FROM Donhangnhap d WHERE d.tinhTrang = :tinhTrang")})
public class Donhangnhap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TongTien")
    private BigDecimal tongTien;
    @Column(name = "TinhTrang")
    private String tinhTrang;
    @Column(name = "ThoiGianDuKien")
    @Temporal(TemporalType.DATE)
    private Date thoiGianDuKien;
    @Column(name = "ThoiGianNhan")
    @Temporal(TemporalType.DATE)
    private Date thoiGianNhan;
    @OneToMany(mappedBy = "iDDonHang")
    private Set<Chitietdonhangnhap> chitietdonhangnhapSet;
    @OneToMany(mappedBy = "iDDonHang")
    private Set<Chiphi> chiphiSet;
    @OneToMany(mappedBy = "iDDonHang")
    @JsonIgnore
    private Set<Hoadonnhap> hoadonnhapSet;
    @JoinColumn(name = "IDKho", referencedColumnName = "ID")
    @ManyToOne
    private Kho iDKho;
    @JoinColumn(name = "IDNhanVien", referencedColumnName = "ID")
    @ManyToOne
    private Nhanvien iDNhanVien;
    @JoinColumn(name = "IDVanChuyen", referencedColumnName = "ID")
    @ManyToOne
    private Vanchuyen iDVanChuyen;

    public Donhangnhap() {
    }

    public Donhangnhap(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Set<Chitietdonhangnhap> getChitietdonhangnhapSet() {
        return chitietdonhangnhapSet;
    }

    public void setChitietdonhangnhapSet(Set<Chitietdonhangnhap> chitietdonhangnhapSet) {
        this.chitietdonhangnhapSet = chitietdonhangnhapSet;
    }

    public Set<Chiphi> getChiphiSet() {
        return chiphiSet;
    }

    public void setChiphiSet(Set<Chiphi> chiphiSet) {
        this.chiphiSet = chiphiSet;
    }

    public Set<Hoadonnhap> getHoadonnhapSet() {
        return hoadonnhapSet;
    }

    public void setHoadonnhapSet(Set<Hoadonnhap> hoadonnhapSet) {
        this.hoadonnhapSet = hoadonnhapSet;
    }

    public Kho getIDKho() {
        return iDKho;
    }

    public void setIDKho(Kho iDKho) {
        this.iDKho = iDKho;
    }

    public Nhanvien getIDNhanVien() {
        return iDNhanVien;
    }

    public void setIDNhanVien(Nhanvien iDNhanVien) {
        this.iDNhanVien = iDNhanVien;
    }

    public Vanchuyen getIDVanChuyen() {
        return iDVanChuyen;
    }

    public void setIDVanChuyen(Vanchuyen iDVanChuyen) {
        this.iDVanChuyen = iDVanChuyen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Donhangnhap)) {
            return false;
        }
        Donhangnhap other = (Donhangnhap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Donhangnhap[ id=" + id + " ]";
    }

    /**
     * @return the thoiGianDuKien
     */
    public Date getThoiGianDuKien() {
        return thoiGianDuKien;
    }

    /**
     * @param thoiGianDuKien the thoiGianDuKien to set
     */
    public void setThoiGianDuKien(Date thoiGianDuKien) {
        this.thoiGianDuKien = thoiGianDuKien;
    }

    /**
     * @return the thoiGianNhan
     */
    public Date getThoiGianNhan() {
        return thoiGianNhan;
    }

    /**
     * @param thoiGianNhan the thoiGianNhan to set
     */
    public void setThoiGianNhan(Date thoiGianNhan) {
        this.thoiGianNhan = thoiGianNhan;
    }

}
