package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.dao.CategoryRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRowRepository extends JpaRepository<CategoryRow, Long> {
    @Query(value = """
            SELECT c.id, c.category_name, cr.category
            FROM category c
            JOIN category_resource cr
            ON c.id = cr.category_id
           """, nativeQuery = true)
    List<CategoryRow> findAllCategoryRows();

    @Query(value = """
            SELECT c.id, c.category_name, cr.category
            FROM category c
            JOIN category_resource cr
            ON c.id = cr.category_id
            WHERE c.category_id = :categoryId
           """, nativeQuery = true)
    List<CategoryRow> findCategoryRowByCategoryId(@Param("categoryId") Long categoryId);
}
