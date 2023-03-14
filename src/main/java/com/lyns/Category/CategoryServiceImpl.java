/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Category;

import com.lyns.Event.Event;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class CategoryServiceImpl implements CategoryService{
@Autowired private CategoryRepository catRepo; 
    @Override
    public Category saveCategory(Category category) {
  try{
  return catRepo.save(category);
  }catch (Exception ex){
  System.out.println(ex.toString());
  
  return null;
  }

    }

    @Override
    public Category editCategory(Category category) {
     try{
  return catRepo.save(category);
  }catch (Exception ex){
  System.out.println(ex.toString());
  
  return null;
  }  }

    @Override
    public List<Category> AllCategory() {
   
    return catRepo.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(long id) {
    return catRepo.findById(id);
    }

    @Override
    public List<Category> AllCategory(Event event) {
    
        return AllCategory().stream().filter(c->c.getEvent().getId()==event.getId()).collect(Collectors.toList());
    }
    
}
