package com.example.BOO.Controller;

import com.example.BOO.DTO.BillProductDTO;
import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.BillProduct;
import com.example.BOO.Repository.BillProductRepository;
import com.example.BOO.Service.BillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    BillProductService billProductService ;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/{id}")
    public ResponseEntity<BillProduct> getBillProduct(@PathVariable Integer id){
        return billProductRepository.findById(id).map(bill -> {
            return new ResponseEntity<>(bill , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("This Bill Product with this Id: " + id+ " does not exist")) ;
    }


    @GetMapping("/query")
    public ResponseEntity<?> getQuery(@RequestParam(required = false) String productName ,@RequestParam(required = false) String categoryName,  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from , @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date to){

        if (from !=null && to!=null && from.after(to)){
            throw new ResourceNotFoundException("From date should be before after") ;
        }
        else{
            String fromStr =  from != null ? formatter.format(from) : null ;
            String toStr = to != null ? formatter.format(to) : null ;

            return new ResponseEntity<>(billProductService.query(productName, categoryName, fromStr, toStr), HttpStatus.OK) ;
        }


    }


}
