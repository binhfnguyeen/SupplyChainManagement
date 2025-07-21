/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.dto;

import java.math.BigDecimal;

/**
 *
 * @author Dell
 */
public class SanphamNhacungcapRequest {
    private int idNhaCungCap;
    private int idSanPham;
    private BigDecimal gia;

    /**
     * @return the idNhaCungCap
     */
    public int getIdNhaCungCap() {
        return idNhaCungCap;
    }

    /**
     * @param idNhaCungCap the idNhaCungCap to set
     */
    public void setIdNhaCungCap(int idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    /**
     * @return the idSanPham
     */
    public int getIdSanPham() {
        return idSanPham;
    }

    /**
     * @param idSanPham the idSanPham to set
     */
    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    /**
     * @return the gia
     */
    public BigDecimal getGia() {
        return gia;
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }
}
