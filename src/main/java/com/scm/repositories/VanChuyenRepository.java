/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Vanchuyen;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface VanChuyenRepository {
    void addOrUpdateVanCuyen(Vanchuyen vc);
    List <Vanchuyen> getAllVanChuyen(Map<String, String> params);
    void deleteVanChuyen(int id);
    Vanchuyen getVanChuyenById(int id);
    BigDecimal getSoTien(Vanchuyen vc);
}
