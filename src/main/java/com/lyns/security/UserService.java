/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.security;


import com.lyns.Contestant.Contestant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class UserService {
    
     @Autowired
    private UserRepository userRepository;
     @Autowired
    private RoleRepository roleRepository;
     
  public String encode(String password){
BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    return encoder.encode(password);
    }
    
    
    
    public users FindUserById(Long id){
    return userRepository.findById(id).get();
    
    }
    public List<users>finallUsers(){
       List  <users> Temp =new ArrayList<>();
   userRepository.findAll().forEach(c->{
    Temp.add(c);
    });
    return Temp;
    }
    public users FindUserByUserName(String UserName){
    return userRepository.getUserByUsername(UserName);
    
    }
    public String updateUser(users user){
    return userRepository.save(user).getUsername()+" successful";
    }
    public String addUser(users user){
   
   user.setPassword( encode(user.getPassword()));
   
   user.setEnabled(true);
   String msg="";
   try{
        if(checkIfUserNameIsAssigned(user).equals("false")){
   //userRepository.save(user);
   return "Welcom Mr./Mrs"+userRepository.save(user).getUsername();
        }else{
       msg= " user exist ";
        }
  // return " Welcome "+user.getUsername();
   }catch (Exception ex){
       System.out.println(ex);
   msg= "error seeting accounts "+ex;
   }
    return msg; 
 }
  public String addRoleTOUser(Long Userid,role newRole){
      Set<role>userRoles=new HashSet<>();
      userRoles.add(newRole);
  users user=FindUserById( Userid);
  user.setRoles(userRoles);
  try{
      
  user=userRepository.save(user);
  return "user roles"+user.getRoles().toString();
  }catch(Exception ex){
  return"error adding role";
  }
 
  }  
   public users CreateCotestantsAccount(Contestant contestant){
        users user=new users();
             BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
        
      
            
            user.setEnabled(true);
            user.setUsername(contestant.getContestantPhoneNumber());
         // user.setShop(contestant);
           user.setPassword(encode.encode(contestant.getContestantIdNumber()+contestant.getContestantPhoneNumber()));
            
            Set<role>roles=new HashSet<>();
           role r= findRoleByName("customer");
            roles.add(r);
            
           user.setRoles(roles);
             if(checkIfUserNameIsAssigned(user).equals("false")){
            addUser(user);
             }else{
           return null;
           }
        
    return user;
    }
    public String checkIfUserNameIsAssigned(users user){
  for(users u:userRepository.findAll()){
      if((u.getUsername()).equals(user.getUsername()))return "true";
     
  }
   return "false";

    }
   public String checkIfRoleIsAssigned(role r){
  for(role u:roleRepository.findAll()){
      if((u.getName()).equals(r.getName()))
          return "true";
     
  }
  
  return "false";
  }
    public String addRole(role r){
    try{
           r.setId(r.getName());
    roleRepository.save(r); return "saved";
      
     
      
   
    }catch (Exception ex){
    return "error"+ex.toString();
    
    }
    
    }
     public List<role>UserRoles(){
 return (List<role>) roleRepository.findAll();
 }
    public role findRoleByName(String name){
     role tR=new role();
        for(role r :UserRoles()){
     if(r.getName().equals(name)){
     tR=r;
     }
     }
    
    return tR;
    }
}

