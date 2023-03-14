/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Voters;

import com.lyns.Contestant.Contestant;
import com.lyns.Event.Event;
import com.lyns.Payment.Payment;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author HP
 */
@Entity
public class Voter {
   @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
   
    private int id;
    
    private String phoneNumber;
    private int numberOfVotes;

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
    
    @OneToOne
    private Payment p;
    
    @ManyToOne
    private Event eventVoter;
    
    @ManyToOne
    private Contestant Contestant;
    
    public Contestant getContestant() {
        return Contestant;
    }

    public Voter() {
    }

    public Voter(String phoneNumber, Event eventVoter, Contestant Contestant) {
        this.phoneNumber = phoneNumber;
        this.eventVoter = eventVoter;
        this.Contestant = Contestant;
    }

    public void setContestant(Contestant Contestant) {
        this.Contestant = Contestant;
    }

    public Voter(String phoneNumber, Payment p, Event eventVoter, Contestant Contestant) {
        this.phoneNumber = phoneNumber;
        this.p = p;
        this.eventVoter = eventVoter;
        this.Contestant = Contestant;
    }
    
    public Event getEventVoter() {
        return eventVoter;
    }

    public void setEventVoter(Event eventVoter) {
        this.eventVoter = eventVoter;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

  
    public Payment getP() {
        return p;
    }

    public void setP(Payment p) {
        this.p = p;
    }
    
    
    
}
