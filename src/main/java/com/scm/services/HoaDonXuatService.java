/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Hoadonxuat;

/**
 *
 * @author Admin
 */
public interface HoaDonXuatService {
    void addHoaDonXuat(Donhangxuat dhx);
    Hoadonxuat getHoaDonXuatById(int id);
}
