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
@Table(name = "chitietdonhangnhap")
@NamedQueries({
    @NamedQuery(name = "Chitietdonhangnhap.findAll", query = "SELECT c FROM Chitietdonhangnhap c"),
    @NamedQuery(name = "Chitietdonhangnhap.findById", query = "SELECT c FROM Chitietdonhangnhap c WHERE c.id = :id"),
    @NamedQuery(name = "Chitietdonhangnhap.findBySoLuong", query = "SELECT c FROM Chitietdonhangnhap c WHERE c.soLuong = :soLuong")})
public class Chitietdonhangnhap implements Serializable {

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
    private Donhangnhap iDDonHang;
    @JoinColumn(name = "IDSanPham", referencedColumnName = "ID")
    @ManyToOne
    private Sanpham iDSanPham;

    public Chitietdonhangnhap() {
    }

    public Chitietdonhangnhap(Integer id) {
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

    public Donhangnhap getIDDonHang() {
        return iDDonHang;
    }

    public void setIDDonHang(Donhangnhap iDDonHang) {
        this.iDDonHang = iDDonHang;
    }

    public Sanpham getIDSanPham() {
        return iDSanPham;
    }

    public void setIDSanPham(Sanpham iDSanPham) {
        this.iDSanPham = iDSanPham;
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
        if (!(object instanceof Chitietdonhangnhap)) {
            return false;
        }
        Chitietdonhangnhap other = (Chitietdonhangnhap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Chitietdonhangnhap[ id=" + id + " ]";
    }

    public void getSoLuong(int soLuong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
