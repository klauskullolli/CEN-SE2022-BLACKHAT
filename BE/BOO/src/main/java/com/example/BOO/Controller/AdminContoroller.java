package com.example.BOO.Controller;

import com.example.BOO.Model.Admin;
import com.example.BOO.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminContoroller {

    @Autowired
    AdminRepository adminRepository ;

    @CrossOrigin
     @GetMapping
     public Admin getAdmin(){
         return adminRepository.findAll().get(0) ;
     }


    @CrossOrigin
     @PostMapping
     public Admin updateAdmin(@Valid  @RequestBody Admin admin){
           Admin newAdmin = adminRepository.findAll().get(0) ;
           newAdmin.setUsername(admin.getUsername());
           newAdmin.setPassword(admin.getPassword());
           newAdmin.setImage(admin.getImage());
           return adminRepository.save(newAdmin) ;
     }

}
