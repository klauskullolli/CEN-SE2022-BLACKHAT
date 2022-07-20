package com.example.BOO.Controller;

import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.BillProduct;
import com.example.BOO.Repository.BillProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/inventory")
public class BillProductController {


    @Autowired
    BillProductRepository billProductRepository ;
    @GetMapping()
    public List<BillProduct> getBillProducts(){
        return billProductRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<BillProduct> getBillProduct(@PathVariable Integer id){
        return billProductRepository.findById(id).map(bill -> {
            return new ResponseEntity<>(bill , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("This Bill Product with this Id: " + id+ " does not exist")) ;
    }





}
