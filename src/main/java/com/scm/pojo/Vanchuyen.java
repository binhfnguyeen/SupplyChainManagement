/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import java.math.BigDecimal;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "vanchuyen")
@NamedQueries({
    @NamedQuery(name = "Vanchuyen.findAll", query = "SELECT v FROM Vanchuyen v"),
    @NamedQuery(name = "Vanchuyen.findById", query = "SELECT v FROM Vanchuyen v WHERE v.id = :id"),
    @NamedQuery(name = "Vanchuyen.findByTinhTrang", query = "SELECT v FROM Vanchuyen v WHERE v.tinhTrang = :tinhTrang"),
    @NamedQuery(name = "Vanchuyen.findByIDDonHang", query = "SELECT v FROM Vanchuyen v WHERE v.iDDonHang = :iDDonHang")})
public class Vanchuyen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TinhTrang")
    private String tinhTrang;
    @Column(name = "SoTien")
    private BigDecimal soTien;
    @OneToMany(mappedBy = "iDVanChuyen")
    @JsonIgnore
    private Set<Donhangxuat> donhangxuatSet;
    @OneToMany(mappedBy = "iDVanChuyen")
    @JsonIgnore
    private Set<Donhangnhap> donhangnhapSet;
    @JoinColumn(name = "IDDoiTacVanChuyen", referencedColumnName = "ID")
    @ManyToOne
    private Doitacvanchuyen iDDoiTacVanChuyen;

    public Vanchuyen() {
    }

    public Vanchuyen(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    
    /**
     * @return the soTien
     */
    public BigDecimal getSoTien() {
        return soTien;
    }

    /**
     * @param soTien the soTien to set
     */
    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public Set<Donhangxuat> getDonhangxuatSet() {
        return donhangxuatSet;
    }

    public void setDonhangxuatSet(Set<Donhangxuat> donhangxuatSet) {
        this.donhangxuatSet = donhangxuatSet;
    }

    public Set<Donhangnhap> getDonhangnhapSet() {
        return donhangnhapSet;
    }

    public void setDonhangnhapSet(Set<Donhangnhap> donhangnhapSet) {
        this.donhangnhapSet = donhangnhapSet;
    }

    public Doitacvanchuyen getIDDoiTacVanChuyen() {
        return iDDoiTacVanChuyen;
    }

    public void setIDDoiTacVanChuyen(Doitacvanchuyen iDDoiTacVanChuyen) {
        this.iDDoiTacVanChuyen = iDDoiTacVanChuyen;
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
        if (!(object instanceof Vanchuyen)) {
            return false;
        }
        Vanchuyen other = (Vanchuyen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Vanchuyen[ id=" + id + " ]";
    }

}