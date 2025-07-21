/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.dto;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class KhoSanphamRequest {
    private int idKho;
    private int idSanPham;
    private int soLuong;
    private Date hanSuDung;

    /**
     * @return the idKho
     */
    public int getIdKho() {
        return idKho;
    }

    /**
     * @param idKho the idKho to set
     */
    public void setIdKho(int idKho) {
        this.idKho = idKho;
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
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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
