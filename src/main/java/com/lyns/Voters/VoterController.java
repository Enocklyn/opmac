/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Voters;

import com.lyns.Contestant.Contestant;
import com.lyns.Contestant.ContestantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HP
 */
@Controller
public class VoterController {
  private @Autowired VoterService vs;
  private @Autowired ContestantService CS;
    @GetMapping("/")
    public String addVoterForm(Model model){
    
        model.addAttribute("newVoter", new Voter());
        return "voter";
    
    }
    
    @PostMapping("/saveVoter")
    public String saveVoter(Model m,@RequestParam("code")String Code, Voter voter){
        System.out.println(Code);
   if(CS.findContestantByCode(new Contestant(Code)).isPresent()){
        voter.setContestant(CS.findContestantByCode(new Contestant(Code)).get());
    voter.setEventVoter(voter.getContestant().getEventContestant());
    m.addAttribute("msg", vs.saveVoter(voter)+" ---->api message"+vs.ConnectToApi());
    return "voter"; 
   }else
    m.addAttribute("msg", "PLEASE THE CODE YOU ENTERED DOES NOT EXIST FOR ANY CONESTANT PLEASE CHECK AGAIN ");
    return "voter";
    }
}
