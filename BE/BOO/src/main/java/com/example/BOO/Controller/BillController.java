package com.example.BOO.Controller;

import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.Bill;
import com.example.BOO.Model.BillDTOResponse;
import com.example.BOO.Model.BillProduct;
import com.example.BOO.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillRepository billRepository ;

    @GetMapping()
    public List<Bill> getBills(){
        return billRepository.findAll();
    }

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

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


    @GetMapping("/query")
    public ResponseEntity<?> getBillsBetweenDate(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date from , @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date to){

        List<Bill> bills ;
        if (from !=null && to!=null && from.after(to)){
            throw new ResourceNotFoundException("From date should be before after") ;
        }else{
            if(from == null && to == null){

                bills =billRepository.findAllBetweenDate(formatter.format(new Date()), formatter.format(new Date()) ) ;
//                return new ResponseEntity<>(bills , HttpStatus.OK) ;
            }
            else if (from == null && to != null ){
                bills =billRepository.findAllBetweenDate(formatter.format(to), formatter.format(to) ) ;
//                return new ResponseEntity<>(bills , HttpStatus.OK) ;
            }

            else if (from != null  && to == null){
                bills =billRepository.findAllBetweenDate(formatter.format(from), formatter.format(new Date()) ) ;
//                return new ResponseEntity<>(bills , HttpStatus.OK) ;
            }
            else{
                bills =billRepository.findAllBetweenDate(formatter.format(from), formatter.format(to) ) ;
//                return new ResponseEntity<>(bills , HttpStatus.OK) ;
            }


            List<BillDTOResponse> billDTOResponses = new ArrayList<>() ;
            for(Bill bill : bills ){
                Double totalPrice = 0.0 ;
               for(BillProduct billProduct : bill.getBillProducts()){
                   totalPrice+= billProduct.getPrice()* billProduct.getAmount() ;
               }
               billDTOResponses.add(new BillDTOResponse(bill.getId(), bill.getCreatedTime(), totalPrice)) ;
            }

            return new ResponseEntity<>(billDTOResponses , HttpStatus.OK) ;
        }

    }

}
