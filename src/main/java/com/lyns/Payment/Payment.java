/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Payment;


import com.lyns.Contestant.Contestant;
import com.lyns.Event.Event;
import com.lyns.Voters.Voter;
import java.io.Serializable;
import java.time.LocalDate;
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
public class Payment implements Serializable {
   
    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
  
    private long id;
    @ManyToOne
    private Event eventPayment;
  
    public long getId() {
        return id;
    }
    private float amount;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Event getEventPayment() {
        return eventPayment;
    }

    public void setEventPayment(Event eventPayment) {
        this.eventPayment = eventPayment;
    }
    @ManyToOne
    private Contestant contestant;

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }
    
     @OneToOne
    private  Voter vote;

    public Voter getVote() {
        return vote;
    }

    public void setVote(Voter vote) {
        this.vote = vote;
    }

    public Payment() {
    }
  private LocalDate pdate;

    public LocalDate getPdate() {
        return pdate;
    }

    public void setPdate(LocalDate pdate) {
        this.pdate = pdate;
    }
  
    public Payment( Event eventPayment, Contestant contestant, Voter vote , float amount) {
       this.amount=amount;
               
        this.eventPayment = eventPayment;
        this.contestant = contestant;
        this.vote = vote;
    }
     
}
