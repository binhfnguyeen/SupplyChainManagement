/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Chitietdonhangnhap;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface ChiTietDonHangNhapRepository {
    void addChiTiet(Chitietdonhangnhap chitiet);
    void deleteChiTietByDonHangId(int id);
    List<Chitietdonhangnhap> getAllChiTiet();
    Chitietdonhangnhap getChiTietById(int id);
    void updateChiTiet(Chitietdonhangnhap chitiet);
    void deleteChiTiet(int id);
    List<Chitietdonhangnhap> findByIdDonHang(int idDonHang);
}
