/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services;

import com.scm.dto.HoaDonXuatResponse;
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
    List<HoaDonXuatResponse> getAllHoaDonXuat(Map <String, String> params);
    void addOrUpdateHoaDonXuat(Hoadonxuat hdx);
    HoaDonXuatResponse getHoaDonNhapById(int id);
    List<Hoadonxuat> getDsHoaDon(Map <String, String> params);
    List<Hoadonxuat> getAllHoaDonXuatByUser(Map<String, String> params,String username);
}
