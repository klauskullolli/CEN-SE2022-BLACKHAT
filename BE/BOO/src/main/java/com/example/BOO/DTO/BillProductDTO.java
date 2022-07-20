package com.example.BOO.DTO;

import java.math.BigDecimal;

public class BillProductDTO {
    private Integer product_Id ;
    private String  name ;
    private BigDecimal amount;
    private Double price ;
    private  String category;

    public BillProductDTO() {
    }

    public BillProductDTO(Integer product_Id, String name, BigDecimal amount, Double price, String category) {
        this.product_Id = product_Id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }

    public Integer getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(Integer product_Id) {
        this.product_Id = product_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BillProductDTO{" +
                "product_Id='" + product_Id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
