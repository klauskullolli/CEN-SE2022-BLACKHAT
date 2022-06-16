package com.example.BOO.Controller;


import com.example.BOO.Enum.Role;
import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.Bill;
import com.example.BOO.Model.Order;
import com.example.BOO.Model.Seller;
import com.example.BOO.Repository.BillRepository;
import com.example.BOO.Repository.OrderRepository;
import com.example.BOO.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerRepository sellerRepository ;

    @Autowired
    BillRepository billRepository;

    @GetMapping()
    public List<Seller> getSellers(){
        return sellerRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable Integer id){
        return sellerRepository.findById(id).map(seller -> {
            return new ResponseEntity<>(seller , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("Seller with Id: " + id+ " does not exist")) ;
    }

    @PostMapping
    public  ResponseEntity<Seller> createSeller(@Valid @RequestBody Seller seller){
        seller.setRole(Role.SELLER);
        seller.setOnWork(false);
        return new ResponseEntity<>(sellerRepository.save(seller), HttpStatus.CREATED) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller ( @PathVariable Integer id, @Valid @RequestBody Seller seller){
        Optional<Seller> sell = sellerRepository.findById(id) ;
        if(sell.isEmpty() ){
            throw new ResourceNotFoundException("Seller with id: " + id+ " does not exist") ;
        }
        else {
            Seller newSeller  = sell.get() ;
            newSeller.setUsername(seller.getUsername());
            newSeller.setPassword(seller.getPassword());
            newSeller.setImage(seller.getImage());

            return   new ResponseEntity<>(createSeller(newSeller).getBody(), HttpStatus.OK) ;
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Seller> deleteSeller ( @PathVariable Integer id){
        Optional<Seller> sell = sellerRepository.findById(id) ;
        if(sell.isEmpty() ){
            throw new ResourceNotFoundException("Seller with id: " + id+ " does not exist") ;
        }
        else {
            sellerRepository.deleteById(id);
            return   new ResponseEntity<>(sell.get(), HttpStatus.OK) ;
        }
    }

    @GetMapping("/{id}/bills")
    public ResponseEntity<?> getSellerBills(@PathVariable Integer id){
        return sellerRepository.findById(id).map(seller -> {
            return new ResponseEntity<>(seller.getBills() , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("Seller with Id: " + id+ " does not exist")) ;
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<?> getSellerOrders(@PathVariable Integer id){
        return sellerRepository.findById(id).map(seller -> {
            return new ResponseEntity<>(seller.getOrders() , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("Seller with Id: " + id+ " does not exist")) ;
    }

    @PutMapping("{id}/turn")
    public ResponseEntity<?> updateWorkingStatus(@PathVariable Integer id , @RequestParam(value = "open") boolean open){
        return sellerRepository.findById(id).map(seller -> {
            seller.setOnWork(open);
            if(seller.getBills().size()>0){
                List<Bill> bills = new ArrayList<>();
                for (Bill bill : seller.getBills()){
                    bill.setSeller(null);
                    bills.add(bill);
                }
                billRepository.saveAll(bills);
                seller.setBills(new ArrayList<>());
            }

            return new ResponseEntity<>(sellerRepository.save(seller) , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("Seller with Id: " + id+ " does not exist")) ;
    }


}
