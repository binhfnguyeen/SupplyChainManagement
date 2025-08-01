/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.Danhgia;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface DanhGiaService {
    void addOrUpdateDanhGia(Danhgia dg);
    List<Danhgia> findDanhGiaByNCC(int nccId);
    List<Danhgia> getAllDanhGia();
    void deleteDanhGia(int id);
    Danhgia getDanhGiaById(int id);
}
