package com.example.BOO.Model;

import com.example.BOO.Enum.Belonging;
import com.example.BOO.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(name = "uc_product_name", columnNames = {"name"})
})
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    @NotNull(message = "Name can't be null")
    private String name ;

    @NotNull(message = "Price can't be null")
    private Double price ;

    @NotNull(message = "Amount can't be null")
    private Integer amount ;

    private String decription ;


    private String image ;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sell> sells = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<BillProduct> billProducts = new ArrayList<>();

    public Product() {
    }
}
