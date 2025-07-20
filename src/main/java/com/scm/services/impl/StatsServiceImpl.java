/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.repositories.StatsRepository;
import com.scm.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private StatsRepository statsRepository;
            
    @Override
    public List<Object[]> statsTonKho() {
       return this.statsRepository.statsTonKho();
    }

    @Override
    public List<Object[]> statsHieuSuatNhaCungCap() {
        return this.statsRepository.statsHieuSuatNhaCungCap();
    }
    
}
