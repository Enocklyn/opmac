/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Contestant;

import com.lyns.Category.Category;
import com.lyns.Event.Event;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HP
 */

@Service
public interface ContestantService {
    public Contestant saveContestant(Contestant c);
    public String updateContestant(Contestant c);
    public  List<Contestant>Contestant();
    public Optional<Contestant> findContestantById(Contestant contestant);
   public Optional<Contestant> findContestantByPhoneNumber(Contestant contestant);
  
    public List<Contestant>getContestantBasedOnEvent(Event event);
     public String updateContestant(Contestant c,MultipartFile file);
   public Optional<Contestant>findContestantByCode(Contestant contestant);
   public Map<Category,List<Contestant>>ContestantsCat(Event event);
   public List<Contestant>ContestnatByCategory(Category category);
   public List<Object> graphData(Category category);
  public Map<Category,List<Object>>graphDatas(Event event);
         
}
