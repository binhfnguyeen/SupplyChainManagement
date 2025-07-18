/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "chiphi")
@NamedQueries({
    @NamedQuery(name = "Chiphi.findAll", query = "SELECT c FROM Chiphi c"),
    @NamedQuery(name = "Chiphi.findById", query = "SELECT c FROM Chiphi c WHERE c.id = :id"),
    @NamedQuery(name = "Chiphi.findByLoaiChiPhi", query = "SELECT c FROM Chiphi c WHERE c.loaiChiPhi = :loaiChiPhi"),
    @NamedQuery(name = "Chiphi.findBySoTien", query = "SELECT c FROM Chiphi c WHERE c.soTien = :soTien")})
public class Chiphi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "LoaiChiPhi")
    private String loaiChiPhi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SoTien")
    private BigDecimal soTien;
    @JoinColumn(name = "IDDonHang", referencedColumnName = "ID")
    @ManyToOne
    private Donhangnhap iDDonHang;

    public Chiphi() {
    }

    public Chiphi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoaiChiPhi() {
        return loaiChiPhi;
    }

    public void setLoaiChiPhi(String loaiChiPhi) {
        this.loaiChiPhi = loaiChiPhi;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public Donhangnhap getIDDonHang() {
        return iDDonHang;
    }

    public void setIDDonHang(Donhangnhap iDDonHang) {
        this.iDDonHang = iDDonHang;
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
        if (!(object instanceof Chiphi)) {
            return false;
        }
        Chiphi other = (Chiphi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Chiphi[ id=" + id + " ]";
    }
    
}
