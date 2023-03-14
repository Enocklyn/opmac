/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Position;

import com.lyns.Payment.Payment;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */

@Service
public interface  PositionService {
  
      public String savePosition(Position p);
    public String editPosition(Position p);
    public Optional<Position>FindPosition(Position p);
    public List<Position>allPosition();
    
    
}
