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
    void addExportInvoice(DonHangXuatRequest dhxr);
    List<Donhangxuat> getDonhangxuat(Map<String,String> params);
    Donhangxuat getDonhangxuatById(int id);
    void UpdateDonhangxuat(Donhangxuat dhx);
    void deleteDonhangxuat(int id);
}
