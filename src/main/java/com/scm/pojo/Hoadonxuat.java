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
@Table(name = "hoadonxuat")
@NamedQueries({
    @NamedQuery(name = "Hoadonxuat.findAll", query = "SELECT h FROM Hoadonxuat h"),
    @NamedQuery(name = "Hoadonxuat.findById", query = "SELECT h FROM Hoadonxuat h WHERE h.id = :id"),
    @NamedQuery(name = "Hoadonxuat.findByTongChiPhi", query = "SELECT h FROM Hoadonxuat h WHERE h.tongChiPhi = :tongChiPhi")})
public class Hoadonxuat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TongChiPhi")
    private BigDecimal tongChiPhi;
    @JoinColumn(name = "IDDonHang", referencedColumnName = "ID")
    @ManyToOne
    private Donhangxuat iDDonHang;

    public Hoadonxuat() {
    }

    public Hoadonxuat(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTongChiPhi() {
        return tongChiPhi;
    }

    public void setTongChiPhi(BigDecimal tongChiPhi) {
        this.tongChiPhi = tongChiPhi;
    }

    public Donhangxuat getIDDonHang() {
        return iDDonHang;
    }

    public void setIDDonHang(Donhangxuat iDDonHang) {
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
        if (!(object instanceof Hoadonxuat)) {
            return false;
        }
        Hoadonxuat other = (Hoadonxuat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Hoadonxuat[ id=" + id + " ]";
    }
    
}
