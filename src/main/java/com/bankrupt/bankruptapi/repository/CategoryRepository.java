package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = """
        SELECT c.*
        FROM category c
        JOIN category_relation cr ON c.id = cr.category_id
        WHERE cr.board_id = :boardId
    """, nativeQuery = true)
    List<Category> findAllCateogryByBoardId(Long boardId);
}
