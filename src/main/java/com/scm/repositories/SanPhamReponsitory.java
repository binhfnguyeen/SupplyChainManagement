/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Sanpham;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface SanPhamReponsitory {
    List<Sanpham> getSanPham(Map<String, String> params);
    Sanpham getSanPhamById(int id);
    void addOrUpdateSanPham(Sanpham s);
    void deleteSanPham(int id);
}
