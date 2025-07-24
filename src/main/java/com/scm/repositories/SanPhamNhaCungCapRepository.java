/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface SanPhamNhaCungCapRepository {
    void add(SanphamNhacungcap spncc);
    List<SanphamNhacungcap> findByIDNhaCungCap(int id);
    BigDecimal getGia(Sanpham s, Nhacungcap ncc);
}
