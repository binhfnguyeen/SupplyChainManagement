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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "nhanvien")
@NamedQueries({
    @NamedQuery(name = "Nhanvien.findAll", query = "SELECT n FROM Nhanvien n"),
    @NamedQuery(name = "Nhanvien.findById", query = "SELECT n FROM Nhanvien n WHERE n.id = :id"),
    @NamedQuery(name = "Nhanvien.findByHoTen", query = "SELECT n FROM Nhanvien n WHERE n.hoTen = :hoTen"),
    @NamedQuery(name = "Nhanvien.findByChucVu", query = "SELECT n FROM Nhanvien n WHERE n.chucVu = :chucVu")})
public class Nhanvien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "HoTen")
    private String hoTen;
    @Column(name = "ChucVu")
    private String chucVu;
    @OneToMany(mappedBy = "iDNhanVien")
    private Set<Donhangxuat> donhangxuatSet;
    @JoinColumn(name = "UserID", referencedColumnName = "ID")
    @OneToOne
    private User userID;
    @OneToMany(mappedBy = "iDNhanVien")
    private Set<Donhangnhap> donhangnhapSet;

    public Nhanvien() {
    }

    public Nhanvien(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public Set<Donhangxuat> getDonhangxuatSet() {
        return donhangxuatSet;
    }

    public void setDonhangxuatSet(Set<Donhangxuat> donhangxuatSet) {
        this.donhangxuatSet = donhangxuatSet;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
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
        if (!(object instanceof Nhanvien)) {
            return false;
        }
        Nhanvien other = (Nhanvien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Nhanvien[ id=" + id + " ]";
    }
    
}
