package com.example.BOO.General;

import com.example.BOO.Enum.Role;
import com.example.BOO.Service.OrderService;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Authorization{
    Role role() default Role.STUDENT;
}


public class RandTest {

    private static boolean isAuthorized(Role r1 , String method, Class c)  {
        Method m = null;
        try {
            m = c.getMethod(method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Authorization auth =  m.getAnnotation(Authorization.class) ;

        if (auth.role()==r1) return  true ;
        else return false ;
    }


    @Test
    @Authorization(role = Role.ADMIN)
    public void test1() {
         Security.setRole(Role.ADMIN);
        System.out.println(isAuthorized(Security.getRole(),"test1" , RandTest.class )) ;

    }
}
