package com.example.BOO.Security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorized{
    String []  roles() default {"STUDENT", "GUEST", "PROFESSOR" , "STAFF", "SELLER", "ADMIN" };
}
