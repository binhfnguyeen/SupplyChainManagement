/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.Lob;
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
@Table(name = "khachhang")
@NamedQueries({
    @NamedQuery(name = "Khachhang.findAll", query = "SELECT k FROM Khachhang k"),
    @NamedQuery(name = "Khachhang.findById", query = "SELECT k FROM Khachhang k WHERE k.id = :id"),
    @NamedQuery(name = "Khachhang.findByTen", query = "SELECT k FROM Khachhang k WHERE k.ten = :ten"),
    @NamedQuery(name = "Khachhang.findByDiaChi", query = "SELECT k FROM Khachhang k WHERE k.diaChi = :diaChi"),
    @NamedQuery(name = "Khachhang.findBySdt", query = "SELECT k FROM Khachhang k WHERE k.sdt = :sdt")})
public class Khachhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Ten")
    private String ten;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "SDT")
    private String sdt;
    @Lob
    @Column(name = "ThongTinLienHe")
    private String thongTinLienHe;
    @OneToMany(mappedBy = "iDKhachHang")
    @JsonIgnore
    private Set<Donhangxuat> donhangxuatSet;
    @JoinColumn(name = "UserID", referencedColumnName = "ID")
    @OneToOne
    @JsonBackReference
    private User userID;

    public Khachhang() {
    }

    public Khachhang(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getThongTinLienHe() {
        return thongTinLienHe;
    }

    public void setThongTinLienHe(String thongTinLienHe) {
        this.thongTinLienHe = thongTinLienHe;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Khachhang)) {
            return false;
        }
        Khachhang other = (Khachhang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Khachhang[ id=" + id + " ]";
    }
    
}
