/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.Sanpham;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface SanPhamService {
    Sanpham getSanPhamById(int id);
    void addOrUpdateSanpham(Sanpham sp);
    List<Sanpham> getAllSanpham(Map<String, String> params);
    void deleteSanpham(int id);
}
