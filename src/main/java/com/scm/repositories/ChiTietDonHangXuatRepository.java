/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Chitietdonhangxuat;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ChiTietDonHangXuatRepository {
    void addChiTiet(Chitietdonhangxuat chitiet);
    void deleteChiTietByDonHangId(int id);
    List<Chitietdonhangxuat> getAllChiTiet();
    Chitietdonhangxuat getChiTietById(int id);
    void updateChiTiet(Chitietdonhangxuat chitiet);
    void deleteChiTiet(int id);
}
