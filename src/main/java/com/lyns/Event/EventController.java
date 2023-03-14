/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Event;

import com.lyns.period.Period;
import com.lyns.period.PeriodService;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HP
 */
@Controller
@RequestMapping("/Admin")
public class EventController {
    @Autowired private EventService ES; @Autowired private PeriodService PS;
     @GetMapping("/home")
  public String welcome(Model model){
     
     // model.addAttribute("user", p.getName());
     //dashboard
     model.addAttribute("dashboard",true);
    model.addAttribute("TE",ES.Events().size());
    model.addAttribute("TC", ES.getTotalRegisteredContestant());
     model.addAttribute("TV", ES.getTotalRegisteredVoters());
   
     return "Admin";
  }
    @GetMapping("/ShowEventForm")
  public String showEventForm(Model model){
     
  //    model.addAttribute("user", p.getName());
     model.addAttribute("event", new Event());
     return "Admin";
  }
  
 @PostMapping("/saveEvent")  
    public String addEvent(Model model,Event event,@RequestParam("endDate") String endDate, 
       @RequestParam("startDate") String startDate){
          model.addAttribute("event", false);
        event.setStatus(Status.Preparation.toString());
        Period p=new Period(startDate, endDate);
       event.setEventPeriod(PS.savedPeriod(p));
        model.addAttribute("msg", ES.saveEvent(event));
  return "Admin";   
   } 
    
  @GetMapping("/getAllEventsByDate/")
public String getAllEventsByDate(Model model, @RequestParam("endDate") String endDate, 
       @RequestParam("startDate") String startDate){
      List<Event>events=ES.Events();
     events.removeIf(c->c.getEventPeriod()==null);
    model.addAttribute("Events", events.stream().filter(C->LocalDate.parse(C.getEventPeriod().getStartDate()).isBefore(LocalDate.parse(endDate))
        ||LocalDate.parse(C.getEventPeriod()
                .getEndDate()).isAfter(LocalDate.parse(startDate)))
            .collect(Collectors.toList()));


return "Admin";
}  
  @GetMapping("/getAllEvents/")
public String getAllEvents(Model model){
    
    List<Event>events=ES.Events();
      
    events.removeIf(c->c.getEventPeriod()==null);
    model.addAttribute("Events", events);


    
return "Admin";
}  
@GetMapping("/getEventTotalVoters/{id}")
public String getEventTotalVoters(@PathVariable("id")Long id,Model model){
   
model.addAttribute("event",ES.findEventById(new Event(id)).get().getVoters().size());
 return "Contestant";
}
}
