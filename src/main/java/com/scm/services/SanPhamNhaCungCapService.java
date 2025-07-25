/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

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
}
