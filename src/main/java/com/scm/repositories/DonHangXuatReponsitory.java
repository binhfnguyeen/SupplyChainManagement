/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories;


import com.scm.dto.DonHangXuatRequest;
import com.scm.pojo.Donhangxuat;
import com.scm.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface DonHangXuatReponsitory {
    void addDonHangXuat(Donhangxuat dhx);
    int addDonHangXuatWithUser(DonHangXuatRequest dhxr,User u);
    List<Donhangxuat> getDonhangxuat(Map<String,String>params);
    Donhangxuat getDonhangxuatById(int id);
    void UpdateDonHangXuat(Donhangxuat dhx);
    void deleteDonHangXuat(int id);
    List<Donhangxuat> getByUserId(int userId);
    List<Donhangxuat> getByNhanVienId(int NhanVienId);
    void partUpdateDonhangXuat(Donhangxuat dhx);
}
