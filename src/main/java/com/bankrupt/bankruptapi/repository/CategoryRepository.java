package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.dao.CategoryRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = """
            SELECT c.id, c.category_name, cr.category
            FROM category c
            JOIN category_resource cr
            ON c.id = cr.category_id
           """, nativeQuery = true)
    List<CategoryRow> findAllCategoryRows();
}
