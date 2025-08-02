/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services;

import com.scm.dto.ChiTietDonHangXuatResponse;
import com.scm.pojo.Chitietdonhangxuat;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ChiTietDonHangXuatService {
    List<Chitietdonhangxuat> getAllChiTiet();
    Chitietdonhangxuat getChiTietById(int id);
    void addOrUpdateChiTiet(Chitietdonhangxuat chitiet);
    void deleteChiTiet(int id);
    List<ChiTietDonHangXuatResponse> getDsSanPham(int id);
}
