/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import java.io.Serializable;
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
import jakarta.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "chitietdonhangxuat")
@NamedQueries({
    @NamedQuery(name = "Chitietdonhangxuat.findAll", query = "SELECT c FROM Chitietdonhangxuat c"),
    @NamedQuery(name = "Chitietdonhangxuat.findById", query = "SELECT c FROM Chitietdonhangxuat c WHERE c.id = :id"),
    @NamedQuery(name = "Chitietdonhangxuat.findBySoLuong", query = "SELECT c FROM Chitietdonhangxuat c WHERE c.soLuong = :soLuong")})
public class Chitietdonhangxuat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @JoinColumn(name = "IDDonHang", referencedColumnName = "ID")
    @ManyToOne
    private Donhangxuat iDDonHang;
    @JoinColumn(name = "IDSanPham", referencedColumnName = "ID")
    @ManyToOne
    private Sanpham iDSanPham;

    @JoinColumn(name = "IDNhaCungCap", referencedColumnName = "ID")
    @ManyToOne
    private Nhacungcap iDNhaCungCap;

    public Chitietdonhangxuat() {
    }

    public Chitietdonhangxuat(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Donhangxuat getIDDonHang() {
        return iDDonHang;
    }

    public void setIDDonHang(Donhangxuat iDDonHang) {
        this.iDDonHang = iDDonHang;
    }

    public Sanpham getIDSanPham() {
        return iDSanPham;
    }

    public void setIDSanPham(Sanpham iDSanPham) {
        this.iDSanPham = iDSanPham;
    }

    public Nhacungcap getIDNhaCungCap() {
        return iDNhaCungCap;
    }

    public void setIDNhaCungCap(Nhacungcap iDNhaCungCap) {
        this.iDNhaCungCap = iDNhaCungCap;
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
        if (!(object instanceof Chitietdonhangxuat)) {
            return false;
        }
        Chitietdonhangxuat other = (Chitietdonhangxuat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Chitietdonhangxuat[ id=" + id + " ]";
    }

    public void setGia(Long price) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
