/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Voters;

import com.lyns.Payment.Payment;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public interface VoterService {
     public Voter saveVoter(Voter v);
    public String editVoter(Voter v);
    public Optional<Voter>FindPayment( Voter v);
    public List<Voter>allPayments();
   public Optional<Voter>findVoterByCode(Voter v);
   public String ConnectToApi();
}
