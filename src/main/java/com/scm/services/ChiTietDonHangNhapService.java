/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.Chitietdonhangnhap;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface ChiTietDonHangNhapService {
    List<Chitietdonhangnhap> getAllChiTiet();
    Chitietdonhangnhap getChiTietById(int id);
    void addOrUpdateChiTiet(Chitietdonhangnhap chitiet);
    void deleteChiTiet(int id);
}
