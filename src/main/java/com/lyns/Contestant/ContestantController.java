/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Contestant;

import com.lyns.Category.Category;
import com.lyns.Event.Event;
import com.lyns.Event.EventService;
import com.lyns.security.UserService;
import com.lyns.security.role;
import com.lyns.security.users;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HP
 */
@Controller
@RequestMapping("/Admin")
public class ContestantController {
    @Autowired private ContestantService CS;
    @Autowired private EventService ES;
    @Autowired private UserService US;
   
    @GetMapping("/addContestantForm/{evetId}")
  public String addContestantForm( Model model,@PathVariable("evetId") Long eventId){
   if(ES.findEventById(new Event(eventId)).isPresent()){
       Contestant cons=new Contestant();cons.setEventContestant(ES.findEventById(new Event(eventId)).get());
  if(ES.findEventById(new Event(eventId)).get().getCategories().isEmpty()){
    Category category =new Category(ES.findEventById(new Event(eventId)).get());
   
    model.addAttribute("ContestantEvent", category.getEvent());
    model.addAttribute("newCartegory",category );
     return "Contestant";
    }
       model.addAttribute("NewContestant", cons);
   model.addAttribute("ContestantEvent", ES.findEventById(new Event(eventId)).get());
        
   }else{
   model.addAttribute("msg", "Please there is no event so far ");
   }   
      return "Contestant";
   
     }
  
  @GetMapping("/Category/{eventId}")
    public String EventDetail( Model model,@PathVariable("eventId") Long eventId){
   if(ES.findEventById(new Event(eventId)).isPresent()){
      // Contestant cons=new Contestant();cons.setEventContestant(ES.findEventById(new Event(eventId)).get());
   // model.addAttribute("NewContestant", cons);
   model.addAttribute("ContestantEvent", ES.findEventById(new Event(eventId)).get());
    model.addAttribute("ContestantChartData",   CS.graphData(ES.findEventById
        (new Event(eventId)).get().getCategories().stream().findFirst().get()));
  
       model.addAttribute("EventDetailsId", eventId);
 
   }else{
   model.addAttribute("msg", "Please there is no event so far ");
   }   
      return "Contestant";
   
     }
    

  @GetMapping("/EventDetails/{eventId}")
    public String EventDetails( Model model,@PathVariable("eventId") Long eventId){
   if(ES.findEventById(new Event(eventId)).isPresent()){
      // Contestant cons=new Contestant();cons.setEventContestant(ES.findEventById(new Event(eventId)).get());
   // model.addAttribute("NewContestant", cons);
   model.addAttribute("ContestantEvent", ES.findEventById(new Event(eventId)).get());
    model.addAttribute("ContestantChartDatas",   CS.graphDatas(ES.findEventById
        (new Event(eventId)).get()));
  
       // model.addAttribute("EventDetails", ES.findEventById(new Event(eventId)).get().getContestant());
  String firstInitials=(ES.findEventById
        (new Event(eventId)).get()).getEventName();
  firstInitials=firstInitials.charAt(0)+"";
   model.addAttribute("initials", firstInitials);
    System.out.println( firstInitials+".............................initials");
     model.addAttribute("EventDetailsId", eventId);
 

   }else{
   model.addAttribute("msg", "Please there is no event so far ");
   }   
      return "Contestant";
   
     }

    @PostMapping("/CofirmContestantForm/")
  public String CofirmContestantForm( Model model,Contestant contestant){ 
      contestant.setContestantPicture("/pro-pics/default.jpg");
   contestant= CS.saveContestant(contestant);
   
   String firstInitials=contestant.getEventContestant().getEventName();
  firstInitials=firstInitials.charAt(0)+"";
   
   contestant.setContestantIdNumber(firstInitials
           + contestant.getId());
   
   contestant= CS.saveContestant(contestant);
   //create new user
     
   
   Event event=contestant.getEventContestant();
   model.addAttribute("ConfirmNewContestant", contestant);
   model.addAttribute("ContestantEvent", event);
      
    return "Contestant";
   
     }
  
  
     @PostMapping("/CofirmContestant/")
  public String CofirmContestant( Model model,Contestant contestant,@RequestParam("file-image")MultipartFile file){ 
  if(file.isEmpty()){
    contestant=CS.findContestantById(contestant).get();
    users u=new users();
      Set<role>roles=new HashSet<>();
  roles.add(US.findRoleByName("Contestant")); 
   u.setRoles(roles);  u.setEnabled(true);u.setUsername(contestant.getContestantPhoneNumber());
   u.setPassword(contestant.getContestantPhoneNumber()+contestant.getContestantIdNumber());
   u.setEvent(contestant.getEventContestant());
  model.addAttribute("msg",    US.addUser(u));
   
    model.addAttribute("msg",   
          CS.saveContestant(contestant).getContestantName()
                  +" with unique code "+contestant.getContestantIdNumber() +" has been confrimed successfully"+"Contestant name is:"+ u.getUsername()
          +"and password is:" +contestant.getContestantPhoneNumber()+contestant.getContestantIdNumber());
  
  
  }else{
        users u=new users();
      Set<role>roles=new HashSet<>();
   roles.add(US.findRoleByName("Contestant")); 
   u.setRoles(roles);
        u.setEnabled(true);
        u.setUsername(contestant.getContestantPhoneNumber());
   u.setPassword(contestant.getContestantPhoneNumber()+contestant.getContestantIdNumber());
   u.setEvent(contestant.getEventContestant());
    model.addAttribute("msg",    US.addUser(u));
 
   model.addAttribute("msg",    CS.updateContestant(contestant, file)
                  +" with unique code "+contestant.getContestantIdNumber() +" has been confrimed successfully"+" Contestant name is: "+ u.getUsername()
         +"and password is:" +contestant.getContestantPhoneNumber()+contestant.getContestantIdNumber());
  
  }    
    model.addAttribute("ContestantEvent", contestant.getEventContestant());
    
      
      return "Contestant";
   
     }
  

  
 @GetMapping("/ShowUpdateContestant/{id}")
 public String UpdateContestantForm(Model model,@PathVariable("id")long id ){
 model.addAttribute("UpdateContestant", CS.findContestantById(new Contestant(id)));
   model.addAttribute("ContestantEvent",  CS.findContestantById(new Contestant(id)).get().getEventContestant());
 
return "Contestant";
 }
 
 @GetMapping("GetContestantBasedOnEvent/{eventid}")
 public String GetContestantBasedOnEvent(Model model, @PathVariable("eventid") long eventid){
      model.addAttribute("EventDetailsId", eventid);
 

 model.addAttribute("AllContestants", CS.getContestantBasedOnEvent
        (ES.findEventById(new Event(eventid)).get()));
 // CS.getContestantBasedOnEvent(new Event(eventid)).forEach(c);
  model.addAttribute("ContestantEvent", ES.findEventById(new Event(eventid)).get());
     
 return "Contestant";
 }
  @GetMapping("GetContestantBasedOnEventCategory/{eventid}")
 public String GetContestantBasedOnEventCategory(Model model, @PathVariable("eventid") long eventid){
 
 model.addAttribute("AllContestantsCategory", CS.ContestantsCat(
        (ES.findEventById(new Event(eventid)).get())));
 // CS.getContestantBasedOnEvent(new Event(eventid)).forEach(c);
      model.addAttribute("EventDetailsId", eventid);
 

  model.addAttribute("ContestantEvent", ES.findEventById(new Event(eventid)).get());
     
 return "Contestant";
 } 
@GetMapping("getCategoryDataForGraph/{eventid}")
public String getStudentDataForGraph(Model model, @PathVariable("eventid") long eventid){
   model.addAttribute("ContestantEvent", ES.findEventById(new Event(eventid)).get());
   model.addAttribute("graphData", ES.findEventById(new Event(eventid)).get().getContestant());
    model.addAttribute("ContestantChartData",   CS.graphData(ES.findEventById
        (new Event(eventid)).get().getCategories().stream().findFirst().get()));
  
         model.addAttribute("EventDetailsId", eventid);
 

    return "Contestant";}


@GetMapping("getDataForGraph/{eventid}")
public String getDataForGraphs(Model model, @PathVariable("eventid") long eventid){
   model.addAttribute("ContestantEvent", ES.findEventById(new Event(eventid)).get());
   model.addAttribute("graphData", ES.findEventById(new Event(eventid)).get().getContestant());
    model.addAttribute("ContestantChartData",   CS.graphDatas(ES.findEventById
        (new Event(eventid)).get()));
    System.out.println( CS.graphDatas(ES.findEventById
        (new Event(eventid)).get()));
        model.addAttribute("EventDetailsId", eventid);
 

    return "Contestant";}

  
}
