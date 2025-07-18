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
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "nhacungcap")
@NamedQueries({
    @NamedQuery(name = "Nhacungcap.findAll", query = "SELECT n FROM Nhacungcap n"),
    @NamedQuery(name = "Nhacungcap.findById", query = "SELECT n FROM Nhacungcap n WHERE n.id = :id"),
    @NamedQuery(name = "Nhacungcap.findByTen", query = "SELECT n FROM Nhacungcap n WHERE n.ten = :ten"),
    @NamedQuery(name = "Nhacungcap.findByDiaChi", query = "SELECT n FROM Nhacungcap n WHERE n.diaChi = :diaChi"),
    @NamedQuery(name = "Nhacungcap.findBySdt", query = "SELECT n FROM Nhacungcap n WHERE n.sdt = :sdt")})
public class Nhacungcap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
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
    @Column(name = "DieuKienThanhToan")
    private String dieuKienThanhToan;
    @OneToMany(mappedBy = "iDNhaCungCap")
    private Set<SanphamNhacungcap> sanphamNhacungcapSet;
    @OneToMany(mappedBy = "iDNhaCungCap")
    private Set<Danhgia> danhgiaSet;

    public Nhacungcap() {
    }

    public Nhacungcap(Integer id) {
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

    public String getDieuKienThanhToan() {
        return dieuKienThanhToan;
    }

    public void setDieuKienThanhToan(String dieuKienThanhToan) {
        this.dieuKienThanhToan = dieuKienThanhToan;
    }

    public Set<SanphamNhacungcap> getSanphamNhacungcapSet() {
        return sanphamNhacungcapSet;
    }

    public void setSanphamNhacungcapSet(Set<SanphamNhacungcap> sanphamNhacungcapSet) {
        this.sanphamNhacungcapSet = sanphamNhacungcapSet;
    }

    public Set<Danhgia> getDanhgiaSet() {
        return danhgiaSet;
    }

    public void setDanhgiaSet(Set<Danhgia> danhgiaSet) {
        this.danhgiaSet = danhgiaSet;
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
        if (!(object instanceof Nhacungcap)) {
            return false;
        }
        Nhacungcap other = (Nhacungcap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Nhacungcap[ id=" + id + " ]";
    }
    
}
