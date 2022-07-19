package com.example.BOO.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String productName;

    private  Integer amount ;

    private  Double price ;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    @JsonIgnore
    private Bill bill;

    public BillProduct() {
    }
}
