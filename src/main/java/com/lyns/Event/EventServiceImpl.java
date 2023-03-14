            /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Event;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class EventServiceImpl implements EventService  {
@Autowired private EventRepository ER;
    @Override
    public String saveEvent(Event c) {
      try{
        ER.save(c);
        return "SAVED EVENT SUUCESSULLY ";
      }catch(Exception ex)
      {
        return "SAVED EVENT SUUCESSULLY "+ex.toString();    
      }
    }

    @Override
    public String updateEvent(Event c) {
     try{
        ER.save(c);
        return "SAVED EVENT SUUCESSULLY ";
      }catch(Exception ex)
      {
        return "SAVED EVENT SUUCESSULLY "+ex.toString();    
      }  }

    @Override
    public List<Event> Events() {
   return ER.findAll();
    }

    @Override
    public Optional<Event> findEventById(Event e) {
    
        return ER.findById(e.getId());
    }

    @Override
    public int getTotalRegisteredContestant() {
    int counnter=0;
    for(Event e:Events()){
    counnter=counnter+e.getContestant().size();
     }
     return counnter;   
        
    }
     @Override
    public int getTotalRegisteredVoters() {
    int counnter=0;
    for(Event e:Events()){
    counnter=counnter+e.getVoters().size();
     }
     return counnter;   
        
    }
}
