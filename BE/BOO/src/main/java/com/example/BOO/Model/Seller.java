package com.example.BOO.Model;

import com.example.BOO.Enum.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Seller", uniqueConstraints = {
        @UniqueConstraint(name = "uc_seller_username", columnNames = {"username"})
})
@Entity
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    @Column(unique = true)
    @NotBlank(message = "UserName can't be null")
    private String username ;

    @NotBlank(message = "Password can't be null")
    private String password;

    private Role role ;

    private String image ;

    private  Boolean onWork ;

    @OneToMany(mappedBy = "seller")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "seller", orphanRemoval = true)
    private List<Bill> bills = new ArrayList<>();



    public Seller() {
    }
}
