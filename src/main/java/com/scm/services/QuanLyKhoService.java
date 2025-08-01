/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.KhoSanpham;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface QuanLyKhoService {
    void addSanPhamToKho(int khoId, int sanPhamId, int soLuong, Date hanSuDung);
    List<KhoSanpham> getSanPhamTrongKho(int khoId);
}
