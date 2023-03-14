/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Position;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class PositionServiceImpl implements PositionService{
private @Autowired PositionRepository PR;
    @Override
    public String savePosition(Position p) {
    try{
        PR.save(p);
        return "SUUCESSFULLY SAVED POSITION";
    }catch(Exception ex){
    return "FATAL ERROR PLEASE CONTACT ADMIN "+ex.toString();
    }
    
    }

    @Override
    public String editPosition(Position p) {
      try{
            PR.save(p);
        return "SUUCESSFULLY EDITTED POSITION";
    }catch(Exception ex){
    return "FATAL ERROR PLEASE CONTACT ADMIN "+ex.toString();
    }
      
        
    }

    @Override
    public Optional<Position> FindPosition(Position p) {
   
    return PR.findById(p.getId());
    }

    @Override
    public List<Position> allPosition() {
    return PR.findAll();
    }
 
}
