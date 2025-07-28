/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Doitacvanchuyen;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface DoiTacVanChuyenRepositoy {
    void addDoitacvanchuyen(Doitacvanchuyen dtvc);
    List<Doitacvanchuyen> getDoiTacVanChuyen(Map<String,String> params);
    Doitacvanchuyen getDoitacvanchuyenById(int id);
}
