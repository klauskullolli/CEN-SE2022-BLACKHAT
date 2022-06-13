package com.example.BOO.Repository;

import com.example.BOO.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List ;
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value = "select * from bill b where b.created_time >= :from and b.created_time <= :to" , nativeQuery = true)
    List<Bill> findAllBetweenDate(String from , String to) ;
}
