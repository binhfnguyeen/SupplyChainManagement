/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

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

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "danhgia")
@NamedQueries({
    @NamedQuery(name = "Danhgia.findAll", query = "SELECT d FROM Danhgia d"),
    @NamedQuery(name = "Danhgia.findById", query = "SELECT d FROM Danhgia d WHERE d.id = :id"),
    @NamedQuery(name = "Danhgia.findByChatLuong", query = "SELECT d FROM Danhgia d WHERE d.chatLuong = :chatLuong"),
    @NamedQuery(name = "Danhgia.findByGiaoHangDungHan", query = "SELECT d FROM Danhgia d WHERE d.giaoHangDungHan = :giaoHangDungHan")})
public class Danhgia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ChatLuong")
    private Integer chatLuong;
    @Column(name = "GiaoHangDungHan")
    private Boolean giaoHangDungHan;
    @JoinColumn(name = "IDNhaCungCap", referencedColumnName = "ID")
    @ManyToOne
    private Nhacungcap iDNhaCungCap;

    public Danhgia() {
    }

    public Danhgia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChatLuong() {
        return chatLuong;
    }

    public void setChatLuong(Integer chatLuong) {
        this.chatLuong = chatLuong;
    }

    public Boolean getGiaoHangDungHan() {
        return giaoHangDungHan;
    }

    public void setGiaoHangDungHan(Boolean giaoHangDungHan) {
        this.giaoHangDungHan = giaoHangDungHan;
    }

    public Nhacungcap getIDNhaCungCap() {
        return iDNhaCungCap;
    }

    public void setIDNhaCungCap(Nhacungcap iDNhaCungCap) {
        this.iDNhaCungCap = iDNhaCungCap;
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
        if (!(object instanceof Danhgia)) {
            return false;
        }
        Danhgia other = (Danhgia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scm.pojo.Danhgia[ id=" + id + " ]";
    }
    
}
