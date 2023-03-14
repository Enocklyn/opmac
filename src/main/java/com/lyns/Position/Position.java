/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Position;

import com.lyns.Event.Event;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author HP
 */
@Entity
public class Position {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO )
    private long Id;
    private String positionName;
    private String category;
    @ManyToOne
    private Event eventPosition;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Event getEventPosition() {
        return eventPosition;
    }

    public void setEventPosition(Event eventPosition) {
        this.eventPosition = eventPosition;
    }
   
}
