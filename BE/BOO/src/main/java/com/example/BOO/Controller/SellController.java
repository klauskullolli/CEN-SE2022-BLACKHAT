package com.example.BOO.Controller;

import com.example.BOO.Exception.OrderNoAmountExeption;
import com.example.BOO.Exception.ResourceNotFoundException;
import com.example.BOO.Model.Product;
import com.example.BOO.Model.Sell;
import com.example.BOO.Repository.ProductRepository;
import com.example.BOO.Repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sell")
public class SellController {

    @Autowired
    SellRepository sellRepository ;


    @Autowired
    ProductRepository productRepository ;

    @GetMapping()
    public List<Sell> getSells(){
        return sellRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sell> getSell(@PathVariable Integer id){
        return sellRepository.findById(id).map(sell -> {
            return new ResponseEntity<>(sell , HttpStatus.OK) ;
        }).orElseThrow(()->new ResourceNotFoundException("Sell with Id: " + id+ " does not exist")) ;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Sell> updateSell ( @PathVariable Integer id,  @RequestBody Sell sell){
        Optional<Sell> sellOptional = sellRepository.findById(id) ;
        if(sellOptional.isEmpty() ){
            throw new ResourceNotFoundException("Seller with id: " + id+ " does not exist") ;
        }
        else {
            Sell newSell  = sellOptional.get() ;

            Product product=  newSell.getProduct() ;

            if(sell.getDescripion()!=null){
                if (!sell.getDescripion().isEmpty()){
                    newSell.setDescripion(sell.getDescripion());
                }
            }

            if( sell.getAmount() !=0 || sell.getAmount()!=null){
                Integer productAmaunt = product.getAmount();
                Integer initialAmount = Integer.valueOf(newSell.getAmount());
                Integer finalAmount =  Integer.valueOf(sell.getAmount());
                if(finalAmount> initialAmount){

                    if (productAmaunt.equals(0)) throw new OrderNoAmountExeption("Product with id: "+ product.getId() + " doesn't any amount") ;

                    productAmaunt = productAmaunt -(Math.abs(initialAmount - finalAmount)) ;

                    if(productAmaunt<0){
                        newSell.setAmount(product.getAmount() + initialAmount);
                        productAmaunt = 0 ;

                    }
                    else {
                        newSell.setAmount(sell.getAmount());
                    }

                }

                if(finalAmount < initialAmount){
                    productAmaunt = productAmaunt + (Math.abs(initialAmount - finalAmount)) ;

                    newSell.setAmount(sell.getAmount());
                }

                newSell.setPrice(newSell.getAmount()* product.getPrice());
                product.setAmount(productAmaunt);

               product= productRepository.save(product) ;

               newSell.setProduct(product);

            }


            return   new ResponseEntity<>(sellRepository.save(newSell), HttpStatus.OK) ;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sell> deleteSell ( @PathVariable Integer id){
        Optional<Sell> sell = sellRepository.findById(id) ;
        if(sell.isEmpty() ){
            throw new ResourceNotFoundException("Seller with id: " + id+ " does not exist") ;
        }
        else {
            sellRepository.deleteById(id);
            return   new ResponseEntity<>(sell.get(), HttpStatus.OK) ;
        }
    }


}
