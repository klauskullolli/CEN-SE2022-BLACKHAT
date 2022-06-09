package com.example.BOO.Controller;

import com.example.BOO.Enum.Belonging;
import com.example.BOO.Exception.DublicatedCategoryExeption;
import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.Category;
import com.example.BOO.Model.Product;
import com.example.BOO.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.* ;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository ;

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryRepository.findAll() ;
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getCategoryById(@PathVariable Integer id){

        return categoryRepository.findById(id)
                .map(category -> new ResponseEntity(category , HttpStatus.OK))
                .orElseThrow(()-> new ResourceNotFoundException("Category with id: " + id+ " does not exist") )  ;
    }

    @PostMapping
    public ResponseEntity<?> createCategory( @Valid @RequestBody  Category category){

        Optional<Category> cat = categoryRepository.findByName(category.getName()) ;
        if(!cat.isPresent()){
            return  new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED) ;
        }
        else {
            return  new ResponseEntity(new DublicatedCategoryExeption("This category is duplicated"), HttpStatus.BAD_REQUEST) ;
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id , @RequestBody Category category){
        Optional<Category> cat = categoryRepository.findById(id) ;
        if(cat.isEmpty() ){
            throw new ResourceNotFoundException("Category with id: " + id+ " does not exist") ;
        }
        else {
             Category newCategory  = cat.get() ;
             newCategory.setName(category.getName());
            return   new ResponseEntity<>(createCategory(newCategory).getBody(), HttpStatus.OK) ;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        Optional<Category> cat = categoryRepository.findById(id) ;
        if(!cat.isPresent() ){
            throw new ResourceNotFoundException("Category with id: " + id+ " does not exist") ;
        }
        else {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(cat.get(), HttpStatus.OK ) ;
        }
    }

    @GetMapping("/{id}/product")
    public  ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Integer id){

        return categoryRepository.findById(id)
                .map(category -> new ResponseEntity<>(category.getProducts() , HttpStatus.OK))
                .orElseThrow(()-> new ResourceNotFoundException("Category with id: " + id+ " does not exist") )  ;
    }

    @GetMapping("/belonging/{name}")
    public ResponseEntity<?> getCategoryByBelonging(@PathVariable String name){

        name = name.toUpperCase(Locale.ROOT) ;
        if(Belonging.CAFETERIA.name().equals(name)){
            return new ResponseEntity<>(categoryRepository.findCategoryByBelonging(Belonging.CAFETERIA) , HttpStatus.OK) ;
        }
        else if(Belonging.CANTINA.name().equals(name)){
            return new ResponseEntity<>(categoryRepository.findCategoryByBelonging(Belonging.CANTINA) , HttpStatus.OK) ;
        }
        else{
            throw  new ResourceNotFoundException("Belonging with name: " + name+ " does not exist") ;
        }


    }

}
