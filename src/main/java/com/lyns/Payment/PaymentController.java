/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Payment;

import com.lyns.Contestant.Contestant;
import com.lyns.Contestant.ContestantService;
import com.lyns.Event.Event;
import com.lyns.Event.EventService;
import com.lyns.Voters.Voter;
import com.lyns.Voters.VoterService;
import com.lyns.security.UserService;
import com.lyns.security.users;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author HP
 */
@Controller
public class PaymentController {
 @Autowired private PaymentService ps;private @Autowired EventService es;
 private @Autowired UserService us;
@Autowired private VoterService vs; @Autowired private ContestantService cs;   
//@GetMapping("/paymentForm") 
   @GetMapping("/savePayment/{id}/{contestantCode}/{amount}")
   public String savePament(Model model,@PathVariable("contestantCode")String contestantCode,@PathVariable("id")String id ,@PathVariable("amount") float amount){
   if(amount>=0.1){
    Contestant con=cs.findContestantByCode(new Contestant(contestantCode)).get();
   Voter v=new Voter(id, con.getEventContestant(), con);
   v=vs.saveVoter(v);
   Payment p=new Payment(con.getEventContestant(), con, v,amount);
       
   ps.savePayment(p);
   
   }else{
   return "invalid amount";
   }
       
   return"";
   }
    @GetMapping("/getPayments/{id}")
    public String savePament(Model model,Principal p,@PathVariable("id")Long id){
    model.addAttribute("payments", es.findEventById(new Event(id)).get().getPayment());
        users u=us.FindUserByUserName(p.getName());
       
    if(u.getRoles().stream().findFirst().get().getName().equals("Admin")){
    return "Admin";
    }else{
    return "EventAdmin";
     
    }
    }
 
}
