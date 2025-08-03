/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.dto;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class SanphamNccDTO {
    private String tenSp;
    private String tenNcc;
    private BigDecimal gia;
    private int idSp;
    private int idNcc;
    private int idSpNcc;
    private String hinh;

    public SanphamNccDTO(String tenSanPham, String tenNhaCungCap, BigDecimal gia,int idSp,int idNcc,int id ,String hinh) {
        this.tenSp = tenSanPham;
        this.tenNcc = tenNhaCungCap;
        this.gia = gia;
        this.idSp=idSp;
        this.idNcc=idNcc;
        this.idSpNcc=id;
        this.hinh=hinh;
    }

    public String getTenSanPham() {
        return tenSp;
    }

    public String getTenNhaCungCap() {
        return tenNcc;
    }

    public BigDecimal getGia() {
        return gia;
    }

    /**
     * @return the idSp
     */
    public int getIdSp() {
        return idSp;
    }

    /**
     * @return the idNcc
     */
    public int getIdNcc() {
        return idNcc;
    }

    /**
     * @return the idSpNcc
     */
    public int getIdSpNcc() {
        return idSpNcc;
    }

    /**
     * @return the hinh
     */
    public String getHinh() {
        return hinh;
    }
}

