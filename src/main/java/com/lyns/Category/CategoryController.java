/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Category;

import com.lyns.Event.Event;
import com.lyns.Event.EventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HP
 */
@Controller
@RequestMapping("/Admin")
public class CategoryController {
    @Autowired private CategoryService CS;
    @Autowired private EventService  Es;
    @GetMapping("showCategoryForm/{eventId}")
    public String showCategoryForm(Model m,@PathVariable("eventId")Long eventId){
    Category category =new Category(Es.findEventById(new Event(eventId)).get());
   
    m.addAttribute("ContestantEvent", category.getEvent());
    m.addAttribute("newCartegory",category );
    return "Contestant";
    }

     @PostMapping("saveCategory/")
    public String saveCategory(Model m,Category category ){
    //Category category =new Category(Es.findEventById(new Event(eventId)).get());
     CS.saveCategory(category);
       m.addAttribute("ContestantEvent", category.getEvent());
       
    m.addAttribute("AllCategory",CS.AllCategory(category.getEvent()) );
    return "Contestant";
    }
    
     @GetMapping("allCategory/{eventId}")
    public String allCategory(Model m ,@PathVariable("eventId")Long eventId){
       m.addAttribute("ContestantEvent",Es.findEventById(new Event(eventId)).get());
         List<Category>categories=CS.AllCategory(Es.findEventById(new Event(eventId)).get()) ;
      Runnable r=()->{
         categories.stream().forEach(c->{
         c.setContestNumer(c.getContestant().size());
         
         });};
      r.run();
     m.addAttribute("AllCategory",categories);
    return "Contestant";
    }
}