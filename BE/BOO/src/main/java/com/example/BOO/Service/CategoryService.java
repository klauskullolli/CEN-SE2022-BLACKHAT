package com.example.BOO.Service;

import com.example.BOO.Model.Category;
import com.example.BOO.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository ;

    public Category getCategoryByName(String name) throws Exception {
        Optional<Category> categoryOptional = categoryRepository.findByName(name) ;
        if(categoryOptional.isPresent()){
            return categoryOptional.get() ;
        }
        else {
            throw new Exception("Category is not present") ;
        }
    }
}
