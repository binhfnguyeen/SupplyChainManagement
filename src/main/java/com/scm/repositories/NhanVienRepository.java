/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Nhanvien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface NhanVienRepository {
    Nhanvien add(Nhanvien nv);
    List<Nhanvien> getDsNhanVien(Map<String, String> params);
    void deleteNhanVien(Integer id);
    Nhanvien getNhanvienById(int id);
    void addOrUpdateNhanvienWithUser(Nhanvien nv);
}
