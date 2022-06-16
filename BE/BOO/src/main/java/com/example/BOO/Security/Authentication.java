package com.example.BOO.Security;

import com.example.BOO.Enum.Role;
import com.example.BOO.Exception.UnAuthenticatedException;
import com.example.BOO.Exception.UnAuthorizedException;


import java.lang.reflect.Method;




public class Authentication {

    private static Role role = null ;
    private  static String username ;
    private static String password ;

    public static Role getRole() {
        return role;
    }

    public static void setRole(Role role) {
        Authentication.role = role;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Authentication.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Authentication.password = password;
    }

    public static void isAuthorized(String method , Class c){
        Method m = null;
        try {
            m = c.getMethod(method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Authorized auth =  m.getAnnotation(Authorized.class) ;

        if(Authentication.role==null){
            throw new UnAuthenticatedException("No User is authenticated") ;
        }  else {
            boolean authorized = false ;
            for (String r : auth.roles()){
                if (r.equals(Authentication.role.name())) {
                    authorized = true ;
                    break;}
            }
            if (!authorized) {
                throw new UnAuthorizedException(String.format("User { suername: %s, role: %s } is not authorized", Authentication.username, Authentication.role));
            }

        }

    }



}
