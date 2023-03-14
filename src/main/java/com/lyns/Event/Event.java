package com.lyns.Event;

import com.lyns.Category.Category;
import com.lyns.Contestant.Contestant;
import com.lyns.Payment.Payment;
import com.lyns.Voters.Voter;
import com.lyns.period.Period;
import com.lyns.security.users;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity

public class Event{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    public long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Event(long Id) {
        this.Id = Id;
    }
    @OneToMany(mappedBy="event")
    private List<users>users;
    
    
    private String Status;
public String getStatus() {
        return Status;
    }

    public List<users> getUsers() {
        return users;
    }

    public void setUsers(List<users> users) {
        this.users = users;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    @OneToMany(mappedBy="eventPayment")
    private List<Payment>payment;
    @OneToMany(mappedBy="eventContestant")
    private List<Contestant>contestant;
 @OneToMany(mappedBy = "event")
 private List<Category>categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
 
     @OneToMany(mappedBy="eventVoter")
           
   private List<Voter>Voters; 
  @OneToOne
  private Period EventPeriod;
private String venue;
    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Period getEventPeriod() {
        return EventPeriod;
    }

    public void setEventPeriod(Period EventPeriod) {
        this.EventPeriod = EventPeriod;
    }
    private String eventName;
    private String location;
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public List<Contestant> getContestant() {
        return contestant;
    }

    public void setContestant(List<Contestant> contestant) {
        this.contestant = contestant;
    }

    public List<Voter> getVoters() {
        return Voters;
    }

    public void setVoters(List<Voter> Voters) {
        this.Voters = Voters;
    }

    public Event() {
    }

}
