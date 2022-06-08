package com.example.BOO.Repository;

import com.example.BOO.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer > {
}
