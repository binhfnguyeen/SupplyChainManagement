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
public class HoaDonNhapResponse {
    private int id;
    private int idDonHang;
    private BigDecimal tongChiPhi;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idDonHang
     */
    public int getIdDonHang() {
        return idDonHang;
    }

    /**
     * @param idDonHang the idDonHang to set
     */
    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    /**
     * @return the tongChiPhi
     */
    public BigDecimal getTongChiPhi() {
        return tongChiPhi;
    }

    /**
     * @param tongChiPhi the tongChiPhi to set
     */
    public void setTongChiPhi(BigDecimal tongChiPhi) {
        this.tongChiPhi = tongChiPhi;
    }
}
