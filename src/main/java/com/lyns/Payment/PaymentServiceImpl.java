/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Payment;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HP
 */
@Component
public class PaymentServiceImpl implements PaymentService {
private @Autowired PaymentRepository pr;
    @Override
    public String savePayment(Payment p) {
    try{
   return pr.save(p)+"saved successfully";
    
    }catch(Exception e){
    System.out.println("Errorr at payment side please check");
   
        return e.toString();
    }    
    }

    @Override
    public String editPayment(Payment p) {
    return pr.save(p).toString();
    }

    @Override
    public Optional<Payment> FindPayment(Payment p) {
    return allPayments().stream().filter(c->c.getId()==p.getId()).findFirst();
    }

    @Override
    public List<Payment> allPayments() {
        
    return pr.findAll();
    }

    
}
