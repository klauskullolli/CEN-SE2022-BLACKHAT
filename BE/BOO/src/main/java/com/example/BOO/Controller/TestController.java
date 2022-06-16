package com.example.BOO.Controller;


import com.example.BOO.Security.Authentication;
import com.example.BOO.Security.Authorized;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Authorized(roles = {"ADMIN"})
    @GetMapping("/auth/admin")
    public String testAdmin(){
        Authentication.isAuthorized("testAdmin", this.getClass());
        return  "Hello Admin" ;
    }

    @Authorized(roles = {"SELLER"})
    @GetMapping("/auth/seller")
    public String testSeller(){
        Authentication.isAuthorized("testSeller", this.getClass());
        return  "Hello Seller" ;
    }
}
