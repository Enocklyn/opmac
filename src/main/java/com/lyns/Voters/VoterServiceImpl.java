/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Voters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class VoterServiceImpl implements VoterService {
  @Autowired  private VoterRepository VR;
    @Override
  @SuppressWarnings("empty-statement")
    public Voter saveVoter(Voter v) {
   try{
       return  VR.save(v);
   }catch(Exception ex){
 return null;
   }
    
    }

    @Override
    public String editVoter(Voter v) {
     try{
    VR.save(v);
    
   return "SAVED SUUCESSFUL";
   }catch(Exception ex){
   return "FATAL ERROR PLEASE CONTACT ADMIN";
   }
    
    
    }

    @Override
    public Optional<Voter> FindPayment(Voter v) {
   return VR.findById(v.getId());
    
    }

    @Override
    public List<Voter> allPayments() {
    
    return VR.findAll();
    }

    @Override
    public Optional<Voter> findVoterByCode(Voter v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  static HttpURLConnection connection;
    @Override
    public String ConnectToApi() {
      try {
          URL url=new URL("https://devp-reqsendmoney-230622-api.hubtel.com/request-money/{0241232915}");
          connection=(HttpURLConnection) url.openConnection();
          System.out.print(connection.getResponseMessage());
      } catch (MalformedURLException ex) {
          Logger.getLogger(VoterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(VoterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
      }
    
      try {
          return  connection.getResponseMessage();
      } catch (IOException ex) {
          Logger.getLogger(VoterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
      return "error===>>>>><<<<<===>>"+ex.toString();
      }
    }
    
}
