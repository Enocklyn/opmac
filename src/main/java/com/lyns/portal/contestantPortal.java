/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.portal;

import com.lyns.Category.CategoryService;
import com.lyns.Contestant.Contestant;
import com.lyns.Contestant.ContestantService;
import com.lyns.Event.Event;
import com.lyns.Event.EventService;
import com.lyns.security.UserService;
import com.lyns.security.users;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HP
 */
@Controller
@RequestMapping("/Contestant")
public class contestantPortal {
    private @Autowired ContestantService CS;
    private @Autowired EventService ES;
    private @Autowired UserService US;
    private @Autowired CategoryService CatS;
    @GetMapping("/viewProfile")
 public String ViewProfile(Model m,Principal p){
  Contestant con=CS.findContestantByCode(new Contestant(p.getName())).get();
  m.addAttribute("contestnat", con);
  return "ContestantPortal";
 }
  @GetMapping("/home")
 public String home(Model m,Principal p){
      users u=US.FindUserByUserName(p.getName());
   // Contestant con=CS.findContestantByPhoneNumber(new Contestant(p.getName())).get();
 m.addAttribute("ContestantEvent", ES.findEventById(u.getEvent()).get());
    return "ContestantPortal";
 }
 
@GetMapping("/EventDetails")
    public String EventDetails( Model model,Principal p){
      users u=US.FindUserByUserName(p.getName());
        
   if(ES.findEventById(u.getEvent()).isPresent()){
      // Contestant cons=new Contestant();cons.setEventContestant(ES.findEventById(new Event(eventId)).get());
   // model.addAttribute("NewContestant", cons);
   model.addAttribute("ContestantEvent", ES.findEventById(u.getEvent()).get());
    model.addAttribute("ContestantChartDatas",   CS.graphDatas(ES.findEventById
        (u.getEvent()).get()));
  
       // model.addAttribute("EventDetails", ES.findEventById(new Event(eventId)).get().getContestant());
  String firstInitials=(ES.findEventById
        (u.getEvent()).get()).getEventName();
  firstInitials=firstInitials.charAt(0)+"";
   model.addAttribute("initials", firstInitials);
    System.out.println( firstInitials+".............................initials");

   }else{
   model.addAttribute("msg", "Please there is no event so far ");
   }   
      return "ContestantPortal";
   
     }
    
  @GetMapping("/GetContestantBasedOnEventCategory")
 public String GetContestantBasedOnEventCategory(Model model,Principal p){
      users u=US.FindUserByUserName(p.getName());
 model.addAttribute("AllContestantsCategory", CS.ContestantsCat(
        (ES.findEventById(u.getEvent()).get())));
 // CS.getContestantBasedOnEvent(new Event(eventid)).forEach(c);
 
  model.addAttribute("ContestantEvent", ES.findEventById(u.getEvent()).get());
     
 return "ContestantPortal";
 } 
 
}
