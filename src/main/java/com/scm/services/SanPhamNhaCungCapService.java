/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface SanPhamNhaCungCapService {
    void addSanPhamToNhaCungCap(int idNhaCungCap, int idSanPham, BigDecimal gia);
    List<SanphamNhacungcap> getSanPhamCuaNhaCungCap(int idNhaCungCap);
    List<SanphamNhacungcap> getAllSanPhamNhaCungCap();
    SanphamNhacungcap getSPNCCById(int id);
    void addOrUpdate(SanphamNhacungcap spcc);
    void deleteSanPhamNhaCungCap(int id);
    BigDecimal getGia(Sanpham s, Nhacungcap ncc);
}
