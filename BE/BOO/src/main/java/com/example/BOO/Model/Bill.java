package com.example.BOO.Model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "bill")
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "access")
    private Boolean access;

    public Bill() {

    }

    public Boolean getAccessible() {
        return access;
    }

    public void setAccessible(Boolean access) {
        this.access = access;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}