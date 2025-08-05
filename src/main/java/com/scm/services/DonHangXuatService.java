/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.Cart;
import com.scm.dto.DonHangXuatRequest;
import com.scm.pojo.Donhangxuat;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface DonHangXuatService {
    void addOrUpdateDonHangXuat(Donhangxuat dhx);
    int addDonHangXuatWithUser(DonHangXuatRequest dhxr,String username);
    List<Donhangxuat> getDonhangxuat(Map<String,String> params);
    Donhangxuat getDonhangxuatById(int id);
    void UpdateDonhangxuat(Donhangxuat dhx);
    void deleteDonhangxuat(int id);
    List<Donhangxuat> getByUserId(int userId);
    List<Donhangxuat> getByNhanVienId(int NhanVienId);
    void partUpdateDonhangXuat(Donhangxuat dhx);
}
