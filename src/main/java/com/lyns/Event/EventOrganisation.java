/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Event;

import com.lyns.Contestant.Contestant;
import org.springframework.stereotype.Component;

/**
 *
 * @author HP
 */
@Component
public class EventOrganisation {
  private Event event;
  
  private Contestant winner;

  private int Totalvotescasted;

    public EventOrganisation() {
    }

    public EventOrganisation(Event event, Contestant winner, int Totalvotescasted) {
        this.event = event;
        this.winner = winner;
        this.Totalvotescasted = Totalvotescasted;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Contestant getWinner() {
        return winner;
    }

    public void setWinner(Contestant winner) {
        this.winner = winner;
    }

    public int getTotalvotescasted() {
        return Totalvotescasted;
    }

    public void setTotalvotescasted(int Totalvotescasted) {
        this.Totalvotescasted = Totalvotescasted;
    }
  
}
