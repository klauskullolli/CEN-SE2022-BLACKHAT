package com.example.BOO.DTO;

import java.util.Date;

public class BillDTOResponse {
    private int id;
    private Date createdTime;
    private Double totalPrice ;

    public BillDTOResponse() {
    }

    public BillDTOResponse(int id, Date createdTime, Double totalPrice) {
        this.id = id;
        this.createdTime = createdTime;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
