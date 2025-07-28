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
    
}
