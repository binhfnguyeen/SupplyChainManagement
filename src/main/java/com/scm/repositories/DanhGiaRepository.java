/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Danhgia;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface DanhGiaRepository {
    void addOrUpdateDanhGia(Danhgia dg);
    List<Danhgia> findDanhGiaByNCC(int id); 
    List<Danhgia> getAllDanhGia();
    void deleteDanhGia(int id);
    Danhgia getDanhGiaById(int id);
}
