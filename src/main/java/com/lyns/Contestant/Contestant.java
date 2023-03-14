/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Contestant;

import com.lyns.Category.Category;
import com.lyns.Event.Event;
import com.lyns.Payment.Payment;
import com.lyns.Position.Position;
import com.lyns.Voters.Voter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author HP
 */
@Entity
public class Contestant implements Serializable {

    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    
    private long id;

    public Contestant() {
    }
private int TotalVotes;

    public int getTotalVotes() {
   return  getVotes().stream().map(Voter::getNumberOfVotes).reduce(0,(a,b)->a+b);
   }

    public void setTotalVotes() {
     getVotes().stream().map(Voter::getNumberOfVotes).reduce(0,(f,g)->f+g);
   }
       @ManyToOne
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
       
    public Contestant(long id) {
        this.id = id;
    }
    
    private String ContestantStageName;
    private String ContestantName;
    private String ContestantPicture;
    private String ContestantPhoneNumber;
    private String ContestantIdNumber;
    
    @OneToMany(mappedBy="Contestant")
    private List<Voter> votes;

    public List<Voter> getVotes() {
        return votes;
    }
   
    
    public Contestant(long id, int TotalVotes, String ContestantStageName, String ContestantName, String ContestantPicture, String ContestantPhoneNumber, String ContestantIdNumber, List<Voter> votes, Event eventContestant, Position p) {
        this.id = id;
        this.TotalVotes = TotalVotes;
        this.ContestantStageName = ContestantStageName;
        this.ContestantName = ContestantName;
        this.ContestantPicture = ContestantPicture;
        this.ContestantPhoneNumber = ContestantPhoneNumber;
        this.ContestantIdNumber = ContestantIdNumber;
        this.votes = votes;
        this.eventContestant = eventContestant;
        this.p = p;
    }

    public Contestant(String ContestantIdNumber) {
        this.ContestantIdNumber = ContestantIdNumber;
    }

    public void setVotes(List<Voter> votes) {
        this.votes = votes;
    }
    @ManyToOne
    private Event eventContestant;
    @OneToMany(mappedBy = "contestant")
    private List<Payment> payments;
    @OneToOne
    private Position p;

    
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public String getContestantStageName() {
        return ContestantStageName;
    }

    public void setContestantStageName(String ContestantStageName) {
        this.ContestantStageName = ContestantStageName;
    }

    public String getContestantName() {
        return ContestantName;
    }

    public void setContestantName(String ContestantName) {
        this.ContestantName = ContestantName;
    }

    public String getContestantPicture() {
        return ContestantPicture;
    }

    public void setContestantPicture(String ContestantPicture) {
        this.ContestantPicture = ContestantPicture;
    }

    public String getContestantPhoneNumber() {
        return ContestantPhoneNumber;
    }

    public void setContestantPhoneNumber(String ContestantPhoneNumber) {
        this.ContestantPhoneNumber = ContestantPhoneNumber;
    }

    public String getContestantIdNumber() {
        return ContestantIdNumber;
    }

    public void setContestantIdNumber(String ContestantIdNumber) {
        this.ContestantIdNumber = ContestantIdNumber;
    }

    public Event getEventContestant() {
        return eventContestant;
    }

    public void setEventContestant(Event eventContestant) {
        this.eventContestant = eventContestant;
    }

    public Position getP() {
        return p;
    }

    public void setP(Position p) {
        this.p = p;
    }

    public Contestant(long id, int TotalVotes, Category category, String ContestantStageName, String ContestantName, String ContestantPicture, String ContestantPhoneNumber, String ContestantIdNumber, List<Voter> votes, Event eventContestant, List<Payment> payments, Position p) {
        this.id = id;
        this.TotalVotes = TotalVotes;
        this.category = category;
        this.ContestantStageName = ContestantStageName;
        this.ContestantName = ContestantName;
        this.ContestantPicture = ContestantPicture;
        this.ContestantPhoneNumber = ContestantPhoneNumber;
        this.ContestantIdNumber = ContestantIdNumber;
        this.votes = votes;
        this.eventContestant = eventContestant;
        this.payments = payments;
        this.p = p;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
