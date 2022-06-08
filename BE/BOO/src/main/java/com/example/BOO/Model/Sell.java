package com.example.BOO.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    @NotNull(message = "Amount can't be null")
    private  Integer amount ;

//    @NotNull(message = "Price can't be null")
    private Double price ;

    private String descripion ;

    @ManyToOne(fetch =FetchType.LAZY,  optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;

    public Sell() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sell sell = (Sell) o;
        return id == sell.id && amount == sell.amount && price.equals(sell.price) && descripion.equals(sell.descripion) && product.equals(sell.product) && order.equals(sell.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, price, descripion, product, order);
    }
}
