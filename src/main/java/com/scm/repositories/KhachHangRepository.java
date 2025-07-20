/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Khachhang;

/**
 *
 * @author Dell
 */
public interface KhachHangRepository {
    Khachhang add(Khachhang kh);
    int soKhachHang();
}
