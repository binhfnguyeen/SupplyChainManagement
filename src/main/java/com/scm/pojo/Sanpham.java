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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "sanpham")
@NamedQueries({
    @NamedQuery(name = "Sanpham.findAll", query = "SELECT s FROM Sanpham s"),
    @NamedQuery(name = "Sanpham.findById", query = "SELECT s FROM Sanpham s WHERE s.id = :id"),
    @NamedQuery(name = "Sanpham.findByTen", query = "SELECT s FROM Sanpham s WHERE s.ten = :ten")})
public class Sanpham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Ten")
    private String ten;
    @Column(name = "Hinh")
    private String hinh;
    @OneToMany(mappedBy = "iDSanPham")
    @JsonIgnore
    private Set<Chitietdonhangnhap> chitietdonhangnhapSet;
    @OneToMany(mappedBy = "iDSanPham")
    @JsonIgnore
    private Set<Chitietdonhangxuat> chitietdonhangxuatSet;
    @OneToMany(mappedBy = "iDSanPham")
    @JsonIgnore
    private Set<KhoSanpham> khoSanphamSet;
    @OneToMany(mappedBy = "iDSanPham")
    @JsonIgnore
    private Set<SanphamNhacungcap> sanphamNhacungcapSet;

    public Sanpham() {
    }

    public Sanpham(Integer id) {
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

    public Set<Chitietdonhangnhap> getChitietdonhangnhapSet() {
        return chitietdonhangnhapSet;
    }

    public void setChitietdonhangnhapSet(Set<Chitietdonhangnhap> chitietdonhangnhapSet) {
        this.chitietdonhangnhapSet = chitietdonhangnhapSet;
    }

    public Set<Chitietdonhangxuat> getChitietdonhangxuatSet() {
        return chitietdonhangxuatSet;
    }

    public void setChitietdonhangxuatSet(Set<Chitietdonhangxuat> chitietdonhangxuatSet) {
        this.chitietdonhangxuatSet = chitietdonhangxuatSet;
    }

    public Set<KhoSanpham> getKhoSanphamSet() {
        return khoSanphamSet;
    }

    public void setKhoSanphamSet(Set<KhoSanpham> khoSanphamSet) {
        this.khoSanphamSet = khoSanphamSet;
    }

    public Set<SanphamNhacungcap> getSanphamNhacungcapSet() {
        return sanphamNhacungcapSet;
    }

    public void setSanphamNhacungcapSet(Set<SanphamNhacungcap> sanphamNhacungcapSet) {
        this.sanphamNhacungcapSet = sanphamNhacungcapSet;
    }

    /**
     * @return the hinh
     */
    public String getHinh() {
        return hinh;
    }

    /**
     * @param hinh the hinh to set
     */
    public void setHinh(String hinh) {
        this.hinh = hinh;
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
        if (!(object instanceof Sanpham)) {
            return false;
        }
        Sanpham other = (Sanpham) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Sanpham[ id=" + id + " ]";
    }

}
