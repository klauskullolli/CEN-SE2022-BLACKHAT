package com.example.BOO.Repository;

import com.example.BOO.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.name = ?1 ")
    Optional<Category> findByName(String name);

}
