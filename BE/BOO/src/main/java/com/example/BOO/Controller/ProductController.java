package com.example.BOO.Controller;



import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.Product;
import com.example.BOO.Repository.CategoryRepository;
import com.example.BOO.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository ;

    @CrossOrigin
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        return productRepository.findById(id).map(product ->new ResponseEntity<>(product, HttpStatus.OK)).
                orElseThrow(()->new ResourceNotFoundException("Product with Id: " + id+ " does not exist"));
    }

    @CrossOrigin
    @PostMapping("category/{category_id}")
    public ResponseEntity<Product> createProduct( @PathVariable Integer category_id, @Valid @RequestBody Product product) {

          return categoryRepository.findById(category_id).map( category -> {
              product.setCategory(category);
              return  new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED) ;

        }).orElseThrow(()-> new ResourceNotFoundException("Category with id: " + category_id+ " does not exist") ) ;
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id,@Valid @RequestBody Product product)  {
        Optional<Product> prod = productRepository.findById(id);
        if (prod.isEmpty()) {
            throw  new ResourceNotFoundException("Product with Id: " + id+ " does not exist") ;
        } else {
            Product newProduct = prod.get();
            newProduct.setAmount(product.getAmount());
            newProduct.setName(product.getName());
            newProduct.setDecription(product.getDecription());
            newProduct.setPrice(product.getPrice());
            newProduct.setImage(product.getImage());
            return createProduct(newProduct.getCategory().getId() ,newProduct);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        Optional<Product> prod = productRepository.findById(id);
        if (prod.isEmpty()) {
            throw  new ResourceNotFoundException("Product with Id: " + id+ " does not exist") ;
        } else {
            productRepository.deleteById(id);
            return new ResponseEntity(prod, HttpStatus.OK);
        }

    }
}
