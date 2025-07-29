/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Hoadonxuat;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface HoaDonXuatRepository {
    void addHoaDonXuat(Donhangxuat dhx);
    void addOrUpdateHoaDonNhap(Hoadonxuat hdx);
    List<Hoadonxuat> getAllHoaDonXuat(Map<String, String> params);
}
