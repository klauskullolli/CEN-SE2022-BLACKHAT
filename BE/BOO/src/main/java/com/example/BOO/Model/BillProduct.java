package com.example.BOO.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bill_product")
@Getter
@Setter
public class BillProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private  int amount ;

    public BillProduct() {
    }
}
