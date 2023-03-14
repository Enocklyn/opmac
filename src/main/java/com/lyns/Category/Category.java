/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Category;

import com.lyns.Contestant.Contestant;
import com.lyns.Event.Event;
import java.util.List;
import java.util.logging.Logger;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long Id;
    
    private String categoryName;
    
    @OneToMany(mappedBy = "category")
    private List<Contestant> contestant;
    
   private int ContestNumer;

    public int getContestNumer() {
            return ContestNumer;
    }

    public void setContestNumer(int ContestNumer) {
        this.ContestNumer = ContestNumer;
    }
    
   
    @ManyToOne
   private Event event; 

    public long getId() {
        return Id;
    }

    public Category(Event event) {
        this.event = event;
    }
    public void setId(long Id) {
        this.Id = Id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Contestant> getContestant() {
        return contestant;
    }

    public void setContestant(List<Contestant> contestant) {
        this.contestant = contestant;
    }

   

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Category(long Id, String categoryName, List<Contestant> contestant, String CategoryName, Event event) {
        this.Id = Id;
        this.categoryName = categoryName;
        this.contestant = contestant;
         this.event = event;
    }
    private static final Logger LOG = Logger.getLogger(Category.class.getName());

    public Category() {
    }

    public Category(long Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "Category{" + "Id=" + Id + ", categoryName=" + categoryName + ", "
                + "contestant=" + contestant + ", CategoryName="+ ", event=" + event + '}';
    }
    
}
