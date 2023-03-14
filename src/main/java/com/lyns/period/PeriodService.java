/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.period;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public interface PeriodService {
    public Period savedPeriod(Period p);
    public String updatePeriod(Period p);
    public List<Period>AllPeriods();
    public Optional <Period> FindPeriod(Period p);
    
    
}
