/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "kho")
@NamedQueries({
    @NamedQuery(name = "Kho.findAll", query = "SELECT k FROM Kho k"),
    @NamedQuery(name = "Kho.findById", query = "SELECT k FROM Kho k WHERE k.id = :id"),
    @NamedQuery(name = "Kho.findByDiaChi", query = "SELECT k FROM Kho k WHERE k.diaChi = :diaChi")})
public class Kho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DiaChi")
    private String diaChi;
    @OneToMany(mappedBy = "iDKho")
    private Set<KhoSanpham> khoSanphamSet;
    @OneToMany(mappedBy = "iDKho")
    private Set<Donhangnhap> donhangnhapSet;

    public Kho() {
    }

    public Kho(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Set<KhoSanpham> getKhoSanphamSet() {
        return khoSanphamSet;
    }

    public void setKhoSanphamSet(Set<KhoSanpham> khoSanphamSet) {
        this.khoSanphamSet = khoSanphamSet;
    }

    public Set<Donhangnhap> getDonhangnhapSet() {
        return donhangnhapSet;
    }

    public void setDonhangnhapSet(Set<Donhangnhap> donhangnhapSet) {
        this.donhangnhapSet = donhangnhapSet;
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
        if (!(object instanceof Kho)) {
            return false;
        }
        Kho other = (Kho) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Kho[ id=" + id + " ]";
    }
    
}
