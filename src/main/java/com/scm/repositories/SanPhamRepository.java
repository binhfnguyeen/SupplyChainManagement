/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Sanpham;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface SanPhamRepository {
    Sanpham getSanPhamById(int id);
    Sanpham addOrUpdateSanpham(Sanpham sp);
    List<Sanpham> getAllSanpham(Map<String, String> params);
    void deleteSanpham(int id);
}
