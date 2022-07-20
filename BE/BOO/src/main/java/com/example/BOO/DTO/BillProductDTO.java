package com.example.BOO.DTO;

public class BillProductDTO {
    private String product_Id ;
    private String  name ;
    private Integer amount;
    private  Double price ;
    private  String category;

    public BillProductDTO() {
    }

    public BillProductDTO(String product_Id, String name, Integer amount, Double price, String category) {
        this.product_Id = product_Id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }

    public String getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(String product_Id) {
        this.product_Id = product_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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
