/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Hoadonnhap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface HoaDonNhapRepository {
    void addOrUpdateHoaDonNhap(Hoadonnhap hdn);
    List<Hoadonnhap> getAllHoaDonNhap(Map<String, String> params);
    Hoadonnhap getHoaDonNhapById(int id);
    void deleteHoaDonNhap(int id);
}
