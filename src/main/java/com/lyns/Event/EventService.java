/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Event;

import com.lyns.Contestant.Contestant;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


/**
 *
 * @author HP
 */

@Service
public interface EventService {
      public String saveEvent(Event  c);
    public String updateEvent (Event  c);
    public  List<Event>Events ();
    public Optional<Event> findEventById(Event e);
     public int getTotalRegisteredContestant ();
       public int getTotalRegisteredVoters();
}
