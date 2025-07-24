/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Cart;
import com.scm.pojo.DonHangXuatRequest;
import com.scm.pojo.Donhangxuat;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface DonHangXuatReponsitory {
    void addDonHangXuat(DonHangXuatRequest dhxr);
    List<Donhangxuat> getDonhangxuat(Map<String,String>params);
    Donhangxuat getDonhangxuatById(int id);
    void UpdateDonHangXuat(Donhangxuat dhx);
    void deleteDonHangXuat(int id);
}
