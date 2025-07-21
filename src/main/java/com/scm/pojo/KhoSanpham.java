/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "kho_sanpham")
@NamedQueries({
    @NamedQuery(name = "KhoSanpham.findAll", query = "SELECT k FROM KhoSanpham k"),
    @NamedQuery(name = "KhoSanpham.findById", query = "SELECT k FROM KhoSanpham k WHERE k.id = :id"),
    @NamedQuery(name = "KhoSanpham.findBySoLuong", query = "SELECT k FROM KhoSanpham k WHERE k.soLuong = :soLuong")})
public class KhoSanpham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Column(name = "HanSuDung")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date hanSuDung;
    @JoinColumn(name = "IDKho", referencedColumnName = "ID")
    @ManyToOne
    private Kho iDKho;
    @JoinColumn(name = "IDSanPham", referencedColumnName = "ID")
    @ManyToOne
    private Sanpham iDSanPham;

    public KhoSanpham() {
    }

    public KhoSanpham(Integer id) {
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

    public Kho getIDKho() {
        return iDKho;
    }

    public void setIDKho(Kho iDKho) {
        this.iDKho = iDKho;
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
        if (!(object instanceof KhoSanpham)) {
            return false;
        }
        KhoSanpham other = (KhoSanpham) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.KhoSanpham[ id=" + id + " ]";
    }

    /**
     * @return the hanSuDung
     */
    public Date getHanSuDung() {
        return hanSuDung;
    }

    /**
     * @param hanSuDung the hanSuDung to set
     */
    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }
    
}
