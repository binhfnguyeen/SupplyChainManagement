/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "doitacvanchuyen")
@NamedQueries({
    @NamedQuery(name = "Doitacvanchuyen.findAll", query = "SELECT d FROM Doitacvanchuyen d"),
    @NamedQuery(name = "Doitacvanchuyen.findById", query = "SELECT d FROM Doitacvanchuyen d WHERE d.id = :id"),
    @NamedQuery(name = "Doitacvanchuyen.findByTen", query = "SELECT d FROM Doitacvanchuyen d WHERE d.ten = :ten"),
    @NamedQuery(name = "Doitacvanchuyen.findByHieuSuat", query = "SELECT d FROM Doitacvanchuyen d WHERE d.hieuSuat = :hieuSuat")})
public class Doitacvanchuyen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Ten")
    private String ten;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HieuSuat")
    private BigDecimal hieuSuat;
    @Lob
    @Column(name = "DieuKienHopTac")
    private String dieuKienHopTac;
    @OneToMany(mappedBy = "iDDoiTacVanChuyen")
    @JsonIgnore
    private Set<Vanchuyen> vanchuyenSet;

    public Doitacvanchuyen() {
    }

    public Doitacvanchuyen(Integer id) {
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

    public BigDecimal getHieuSuat() {
        return hieuSuat;
    }

    public void setHieuSuat(BigDecimal hieuSuat) {
        this.hieuSuat = hieuSuat;
    }

    public String getDieuKienHopTac() {
        return dieuKienHopTac;
    }

    public void setDieuKienHopTac(String dieuKienHopTac) {
        this.dieuKienHopTac = dieuKienHopTac;
    }

    public Set<Vanchuyen> getVanchuyenSet() {
        return vanchuyenSet;
    }

    public void setVanchuyenSet(Set<Vanchuyen> vanchuyenSet) {
        this.vanchuyenSet = vanchuyenSet;
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
        if (!(object instanceof Doitacvanchuyen)) {
            return false;
        }
        Doitacvanchuyen other = (Doitacvanchuyen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Doitacvanchuyen[ id=" + id + " ]";
    }
    
}
