/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.security;


import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author enock
 */
@Controller
public class UserController {
    @Autowired
    private UserService UDSI;
       @Autowired
    private UserService US ;
          @GetMapping("/RouteUser")
    public String showAddUserPage(Model model,Principal principal,Authentication authentication){
       
        Optional <? extends GrantedAuthority> role=authentication.getAuthorities().stream().findFirst();
   
        if(role.get().getAuthority().equals("Admin")){
     return "redirect:/Admin/home";
   
      }
       if(role.get().getAuthority().equals("EventAdminPortal")){
     return "redirect:/EventAdmin/home";
   
      }  
        if(role.get().getAuthority().equals("Contestant")){
     return "redirect:/Contestant/home";
   
      }  
 
   return "redirect:/login/";
    }
    
    
   
    @GetMapping("/showAddUserPage")
    public String showAddUserPage(Model model){
    model.addAttribute("user",new users());
    
    model.addAttribute("roles",US.UserRoles());
    return "Admin";
    }
    @GetMapping("/AllUsers/{shopId}")
    public String Users(Model model,@PathVariable("shopId")int shopId){
     //  shop s=new shop();s.setShopId(shopId);List<users>workers=sS.findData(s).getWorkes() ;
       //workers.removeIf(c->c.isEnabled()==false);
      //  model.addAttribute("users",workers);
    
    return "Admin";
    }
    @PostMapping("/addUser")
    public String addUser(Principal p ,Model model, users user,@ModelAttribute("role") String role ){
        users TemAdmin=   UDSI.FindUserByUserName(p.getName());Set<role>roles=new HashSet<>();
      role Temprole=UDSI.findRoleByName(role); roles.add(Temprole);
      
    user.setEnabled(true);user.setRoles(roles);
    // user.setBusiness(TemAdmin.getBusiness());
     // shop s=user.getShop(); 
         //   List<users>u=s.getWorkes();u.add(user);
            
    model.addAttribute("msg" , UDSI.addUser(user));
     //  s.setWorkes(u); 
     //sS.save(s);
       if(Temprole.equals("Employee")) {
    	   
    	   return "Empployee";
    	   
       }
    return "Admin";    
    } 
    
    
    @GetMapping("/updateUser")
    public String ShowUpdateUserForm(Model model,Principal p){
    model.addAttribute("UpdateUser",US.FindUserByUserName(p.getName()));
        List<role>roles=US.UserRoles();
        roles.removeIf(c->c.getName().equals("Student"));
        roles.removeIf(c->c.getName().equals("Teacher"));
    model.addAttribute("Roles",roles);
    if(US.FindUserByUserName(p.getName()).getRoles().stream().findFirst().equals("Employee")) {
    	return "Employee";
    	
    }
    return "Admin";
    }
    @PostMapping("/saveUpdate")
    public String saveUpdateUser(users User){
    
    return US.addUser(User);
    
    }
   
    
    @GetMapping("/AddUserAdmin")
    
    public@ResponseBody String addAdminUser(){
   // Bu
        users user=new users();
    user.setUsername("Anonymous");
    user.setPassword("k");
//   Business business=new Business();
    Set<role>rs=new HashSet<>();rs.add(US.findRoleByName("Admin"));
        
    user.setRoles(rs);
    return  UDSI.addUser(user);
            
    } 
    @GetMapping("/freezeUserAccounts/{id}/{Eventid}")
    public String freezUserId(Model model,@PathVariable("id")Long userId,@PathVariable("Eventid")Long Eventid){
     users s=UDSI.FindUserById(userId);
//     int shopId=s.getShop().getShopId();
    s.setEnabled(false);
    model.addAttribute("msg",UDSI.updateUser(s));
    return "redirect:/EventAdmin/"+s.getEvent()+"/";
    }
    @GetMapping("/AddUserRoles")
    public @ResponseBody String AddUserRoles(){
    Set<role>Roles=new HashSet<>();
    Roles.add(new role("Admin"));
    Roles.add(new role("EventAdmin"));
    Roles.add(new role("Contestant"));
    for(role r:Roles){
     US.addRole(r);
      }
    
 return "Admin";   
}
    @GetMapping("/login")
    public String ViewLogInPage(){
    
    return "SignUp";
    }
    
    
}