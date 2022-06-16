package com.example.BOO.General;

import com.example.BOO.Enum.Role;

public class Security {

    private static Role role = null ;
    private  static String username ;
    private static String password ;

    public static Role getRole() {
        return role;
    }

    public static void setRole(Role role) {
        Security.role = role;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Security.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Security.password = password;
    }
}
