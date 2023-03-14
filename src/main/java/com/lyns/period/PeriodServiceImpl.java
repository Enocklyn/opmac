/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.period;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class PeriodServiceImpl implements PeriodService{
private @Autowired PeriodRepository PR;
    @Override
    public Period savedPeriod(Period p) {
           try{
      return     PR.save(p);
       
      }catch(Exception ex)
      {
        return null;
      }
    }

    @Override
    public String updatePeriod(Period p) {
       try{
       PR.save(p);
        return "SAVED EVENT SUUCESSULLY ";
      }catch(Exception ex)
      {
        return "SAVED EVENT SUUCESSULLY "+ex.toString();    
      }
    }

    @Override
    public List<Period> AllPeriods() {
    return PR.findAll();
    
    }

    @Override
    public Optional<Period> FindPeriod(Period p) {
        
       return PR.findById(p.getId());
    
    }
    
}
