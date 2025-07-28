/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services;

import com.scm.dto.HoaDonNhapResponse;
import com.scm.pojo.Hoadonnhap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface HoaDonNhapService {
    void xuatHoaDonNhap(int id);
    List<HoaDonNhapResponse> getAllHoaDonNhap(Map <String, String> params);
    HoaDonNhapResponse getHoaDonNhapById(int id);
    void addOrUpdateHoaDonNhap(Hoadonnhap hdn);
    List<Hoadonnhap> getDsHoaDon(Map<String, String> params);
    Hoadonnhap getHoaDonById(int id);
    void deleteHoaDonNhap(int id);
}
