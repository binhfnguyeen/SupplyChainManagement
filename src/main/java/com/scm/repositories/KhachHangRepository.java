/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Khachhang;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface KhachHangRepository {
    Khachhang add(Khachhang kh);
    int soKhachHang();
    List<Khachhang> getDsKhachHang(Map<String, String> params);
    void deleteKhachHang(Integer id);
    Khachhang getKhachHangById(int id);
    void addOrUpdateKhachHangWithUser(Khachhang nv);
    Khachhang getKhachHangByKhachHangName(String name);
}
