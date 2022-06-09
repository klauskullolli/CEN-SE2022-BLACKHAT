package com.example.BOO.Controller;

import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.Bill;
import com.example.BOO.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillRepository billRepository ;

    @GetMapping()
    public List<Bill> getBills(){
        return billRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable Integer id){
        return billRepository.findById(id).map(bill -> {
            return new ResponseEntity<>(bill , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("Bill with Id: " + id+ " does not exist")) ;
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Bill> deleteBill (@PathVariable Integer id){
        Optional<Bill> billOptional = billRepository.findById(id) ;
        if(billOptional.isEmpty() ){
            throw new ResourceNotFoundException("Bill with id: " + id+ " does not exist") ;
        }
        else {
            billRepository.deleteById(id);
            return   new ResponseEntity<>(billOptional.get(), HttpStatus.OK) ;
        }
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getBillProducts(@PathVariable Integer id){
        return billRepository.findById(id).map(bill -> {
            return new ResponseEntity<>(bill.getBillProducts() , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("Bill with Id: " + id+ " does not exist")) ;
    }
}
