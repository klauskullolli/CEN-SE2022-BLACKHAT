package com.example.BOO.Model;


import com.example.BOO.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private Role role ;

    private String number ;

    @OneToOne(mappedBy = "client",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
    private Order order;

    public Client() {
    }
}
