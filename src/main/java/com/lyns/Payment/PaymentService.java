/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Payment;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public interface PaymentService {
    public String savePayment(Payment p);
    public String editPayment(Payment p);
    public Optional<Payment>FindPayment(Payment p);
    public List<Payment>allPayments();
    
}
