/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Hoadonxuat;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface HoaDonXuatService {
    void addHoaDonXuat(Donhangxuat dhx);
    Hoadonxuat getHoaDonXuatById(int id);
    void xuatHoaDonXuat(int id);
    List<Hoadonxuat> getAllHoaDonXuat(Map <String, String> params);
    void addOrUpdateHoaDonXuat(Hoadonxuat hdx);
    List<Hoadonxuat> getDsHoaDon(Map <String, String> params);
}
