package com.example.BOO.General;

import com.example.BOO.Service.OrderService;
import org.junit.jupiter.api.Test;


public class RandTest {

    @Test
    public void test1(){

        System.out.println(OrderService.randInt(1 ,4)) ;

    }
}
