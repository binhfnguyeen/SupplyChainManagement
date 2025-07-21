/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.Nhanvien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface NhanVienService {
    List<Nhanvien> getDsNhanVien(Map<String, String> params);
     void deleteNhanVien(Integer id);
     void addOrUpdateNhanvienWithUser(Nhanvien nv);
     Nhanvien getNhanvienById(int id);
     int soNhanVien();
}
