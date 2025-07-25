/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.Vanchuyen;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface VanChuyenService {
    void addOrUpdateVanCuyen(Vanchuyen vc);
    List <Vanchuyen> getAllVanChuyen(Map<String, String> params);
    void deleteVanChuyen(int id);
    Vanchuyen getVanChuyenById(int id);
}
