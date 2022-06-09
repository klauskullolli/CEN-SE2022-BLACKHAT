package com.example.BOO.Model;

import com.example.BOO.Enum.Belonging;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category", uniqueConstraints = {
        @UniqueConstraint(name = "uc_category_name", columnNames = {"name"})
})
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    @Column( nullable = false, unique = true)
    @NotBlank(message = "Name should not be empty")
    private String name ;

    @NotNull(message = "Belonging can't be null")
    private Belonging belonging ;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Category() {
    }


}
