/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Category;

import com.lyns.Event.Event;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public interface CategoryService {
    public Category saveCategory(Category category);
    public Category editCategory(Category category);
    public List<Category>AllCategory();
    public Optional<Category>findCategoryById(long id);
    public List<Category>AllCategory(Event event);
}
