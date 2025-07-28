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
@Table(name = "sanpham_nhacungcap")
@NamedQueries({
    @NamedQuery(name = "SanphamNhacungcap.findAll", query = "SELECT s FROM SanphamNhacungcap s"),
    @NamedQuery(name = "SanphamNhacungcap.findById", query = "SELECT s FROM SanphamNhacungcap s WHERE s.id = :id"),
    @NamedQuery(name = "SanphamNhacungcap.findByGia", query = "SELECT s FROM SanphamNhacungcap s WHERE s.gia = :gia")})
    @NamedQuery(
            name = "SanphamNhacungcap.findBySanphamAndNcc",
            query = "SELECT s FROM SanphamNhacungcap s WHERE s.iDSanPham = :sp AND s.iDNhaCungCap = :ncc"
    )

public class SanphamNhacungcap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Gia")
    private BigDecimal gia;
    @JoinColumn(name = "IDNhaCungCap", referencedColumnName = "ID")
    @ManyToOne
    private Nhacungcap iDNhaCungCap;
    @JoinColumn(name = "IDSanPham", referencedColumnName = "ID")
    @ManyToOne
    private Sanpham iDSanPham;

    public SanphamNhacungcap() {
    }

    public SanphamNhacungcap(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public Nhacungcap getIDNhaCungCap() {
        return iDNhaCungCap;
    }

    public void setIDNhaCungCap(Nhacungcap iDNhaCungCap) {
        this.iDNhaCungCap = iDNhaCungCap;
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
        if (!(object instanceof SanphamNhacungcap)) {
            return false;
        }
        SanphamNhacungcap other = (SanphamNhacungcap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.SanphamNhacungcap[ id=" + id + " ]";
    }

}
