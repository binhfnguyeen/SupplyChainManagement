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
import java.util.Date;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "donhangxuat")
@NamedQueries({
    @NamedQuery(name = "Donhangxuat.findAll", query = "SELECT d FROM Donhangxuat d"),
    @NamedQuery(name = "Donhangxuat.findById", query = "SELECT d FROM Donhangxuat d WHERE d.id = :id"),
    @NamedQuery(name = "Donhangxuat.findByTongTien", query = "SELECT d FROM Donhangxuat d WHERE d.tongTien = :tongTien"),
    @NamedQuery(name = "Donhangxuat.findByTinhTrang", query = "SELECT d FROM Donhangxuat d WHERE d.tinhTrang = :tinhTrang")})
public class Donhangxuat implements Serializable {

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
    @JoinColumn(name = "IDKhachHang", referencedColumnName = "ID")
    @ManyToOne
    private Khachhang iDKhachHang;
    @JoinColumn(name = "IDNhanVien", referencedColumnName = "ID")
    @ManyToOne
    private Nhanvien iDNhanVien;
    @JoinColumn(name = "IDVanChuyen", referencedColumnName = "ID")
    @ManyToOne
    private Vanchuyen iDVanChuyen;
    @OneToMany(mappedBy = "iDDonHang")
    @JsonIgnore
    private Set<Chitietdonhangxuat> chitietdonhangxuatSet;
    @OneToMany(mappedBy = "iDDonHang")
    @JsonIgnore
    private Set<Hoadonxuat> hoadonxuatSet;

    public Donhangxuat() {
    }

    public Donhangxuat(Integer id) {
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

    public Khachhang getIDKhachHang() {
        return iDKhachHang;
    }

    public void setIDKhachHang(Khachhang iDKhachHang) {
        this.iDKhachHang = iDKhachHang;
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

    public Set<Chitietdonhangxuat> getChitietdonhangxuatSet() {
        return chitietdonhangxuatSet;
    }

    public void setChitietdonhangxuatSet(Set<Chitietdonhangxuat> chitietdonhangxuatSet) {
        this.chitietdonhangxuatSet = chitietdonhangxuatSet;
    }

    public Set<Hoadonxuat> getHoadonxuatSet() {
        return hoadonxuatSet;
    }

    public void setHoadonxuatSet(Set<Hoadonxuat> hoadonxuatSet) {
        this.hoadonxuatSet = hoadonxuatSet;
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
        if (!(object instanceof Donhangxuat)) {
            return false;
        }
        Donhangxuat other = (Donhangxuat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Donhangxuat[ id=" + id + " ]";
    }

    public void setCreatedDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
