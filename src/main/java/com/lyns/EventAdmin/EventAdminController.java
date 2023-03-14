package com.lyns.EventAdmin;

import com.lyns.Category.Category;
import com.lyns.Contestant.Contestant;
import com.lyns.Contestant.ContestantService;
import com.lyns.Event.Event;
import com.lyns.Event.EventService;
import com.lyns.Payment.Payment;
import com.lyns.security.UserService;
import com.lyns.security.role;
import com.lyns.security.users;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/EventAdmin")
public class EventAdminController {
    
   @Autowired EventService ES; @Autowired private ContestantService CS;
   @Autowired UserService US;
 @GetMapping("/home")
  public String welcome(Model model,Principal p){
     users u  = US.FindUserByUserName(p.getName());
     // model.addAttribute("user", p.getName());
     //dashboard
     
     model.addAttribute("dashboardEventAdmin",true);
    model.addAttribute("TC",u.getEvent().getContestant().size());
    model.addAttribute("TV", u.getEvent().getVoters().size());
     model.addAttribute("TP", u.getEvent().getPayment()
             .stream().map(Payment::getAmount)
             .reduce(0.f, (a,t)->a+t));
    model.addAttribute("ContestantEvent", u.getEvent());
   
     return "EventAdmin";
  }
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
  
       // model.addAttribute("EventDetails", ES.findEventById(new Event(eventId)).get().getContestant());
 
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
  


}