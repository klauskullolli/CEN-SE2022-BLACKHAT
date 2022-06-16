package com.example.BOO.Controller;

import com.example.BOO.Enum.Role;
import com.example.BOO.Model.Admin;
import com.example.BOO.Model.Seller;
import com.example.BOO.Repository.AdminRepository;
import com.example.BOO.Repository.SellerRepository;
import com.example.BOO.Security.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List ;
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    SellerRepository sellerRepository ;

    @Autowired
    AdminRepository adminRepository ;

     @PostMapping("/seller")
     public ResponseEntity<?> authenticateSeller(@RequestBody Map<String, String> input){
         List<String> errors = new ArrayList<>();
         Map<String , Object> output = new LinkedHashMap<>() ;
         String username = input.get("username") ;
         String password = input.get("password") ;
         if(username==null){
             errors.add("Username is empty") ;
         }
         if (password==null){
             errors.add("Password is empty")  ;
         }
         if(username!=null && password!=null){
               Seller seller = sellerRepository.findByUsername(username);
               if(seller==null) errors.add("Seller with username: " + username +" does not exist") ;
               if(seller!=null){
                   if(seller.getUsername().equals(username) && seller.getPassword().equals(password)){
                       Authentication.setRole(seller.getRole());
                       Authentication.setUsername(seller.getUsername());
                       Authentication.setPassword(seller.getPassword());
                       output.put("message", "Seller with username: "+username +" is authenticated") ;
                       return new ResponseEntity<>(output, HttpStatus.OK) ;
                   }
                   else{
                       output.put("message", "Seller with username: "+username +" is not authenticated") ;
                       return new ResponseEntity<>(output, HttpStatus.FORBIDDEN) ;
                   }
               }
         }

         output.put("message", errors) ;

         return new ResponseEntity<>(output, HttpStatus.FORBIDDEN) ;

     }

    @PostMapping("/admin")
    public ResponseEntity<?> authenticateAdmin(@RequestBody Map<String, String> input){
        List<String> errors = new ArrayList<>();
        Map<String , Object> output = new LinkedHashMap<>() ;
        String username = input.get("username") ;
        String password = input.get("password") ;
        if(username==null){
            errors.add("Username is empty") ;
        }
        if (password==null){
            errors.add("Password is empty")  ;
        }
        if(username!=null && password!=null){
            Admin admin = adminRepository.findAll().get(0);
            if(admin==null) errors.add("Admin with username: " + username +" does not exist") ;
            if(admin!=null){
                if(admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                    Authentication.setRole(Role.ADMIN);
                    Authentication.setUsername(admin.getUsername());
                    Authentication.setPassword(admin.getPassword());
                    output.put("message", "Admin with username: "+username +" is authenticated") ;
                    return new ResponseEntity<>(output, HttpStatus.OK) ;
                }
                else{
                    output.put("message", "Admin with username: "+username +" is not authenticated") ;
                    return new ResponseEntity<>(output, HttpStatus.FORBIDDEN) ;
                }
            }
        }

        output.put("message", errors) ;

        return new ResponseEntity<>(output, HttpStatus.FORBIDDEN) ;

    }

    @GetMapping("/remove")
    public ResponseEntity<?> removeAuthenitcation(){
         Authentication.setRole(null);
         Authentication.setUsername(null);
         Authentication.setPassword(null);
        return new ResponseEntity<>("Authentication removed", HttpStatus.OK) ;
    }


}
