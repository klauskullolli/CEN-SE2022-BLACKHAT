package com.example.BOO.Controller;


import com.example.BOO.Enum.Role;
import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.Seller;
import com.example.BOO.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class SellerController {

    @Autowired
    SellerRepository sellerRepository ;

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

}
