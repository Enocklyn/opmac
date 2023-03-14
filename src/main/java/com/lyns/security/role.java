/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.security;

/**
 *
 * @author enock
 *
    // remaining getters and setters   
}
 */
import javax.persistence.*;
 
@Entity
@Table(name = "roles")
public class role {
    @Id
    @Column(name = "role_id")
     private String id;

    public role(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
     
    private String name;
    public String getId() {
        return id;
    }

    public role() {
    }

    public String getName() {
        return name;
    }
}
 