/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.dto;

import com.scm.pojo.Cart;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DonHangXuatRequest {
    private int idNhanVien;
    private List<Cart> carts;
    private String tinhTrang;
    private int iDVanChuyen;

    /**
     * @return the idNhanVien
     */
    public int getIdNhanVien() {
        return idNhanVien;
    }

    /**
     * @param idNhanVien the idNhanVien to set
     */
    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    /**
     * @return the carts
     */
    public List<Cart> getCarts() {
        return carts;
    }

    /**
     * @param carts the carts to set
     */
    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    /**
     * @return the tinhTrang
     */
    public String getTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    /**
     * @return the iDVanChuyen
     */
    public int getiDVanChuyen() {
        return iDVanChuyen;
    }

    /**
     * @param iDVanChuyen the iDVanChuyen to set
     */
    public void setiDVanChuyen(int iDVanChuyen) {
        this.iDVanChuyen = iDVanChuyen;
    }
    
}
