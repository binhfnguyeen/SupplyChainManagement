/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public interface SanPhamNhaCungCapReponsitory {
    BigDecimal getGia(Sanpham s, Nhacungcap ncc);
}
