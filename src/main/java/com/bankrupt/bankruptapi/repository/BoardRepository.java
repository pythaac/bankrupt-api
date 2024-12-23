package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "SELECT id FROM board", nativeQuery = true)
    List<Long> findAllBoardIds();

    @Query(value = """
        SELECT b.*
        FROM board b
        JOIN category_relation cr ON b.id = cr.board_id
        WHERE cr.category_id = :categoryId
    """, nativeQuery = true)
    List<Board> findAllBoardByCategoryId(Long categoryId);

    @Query(value = """
        SELECT updated
        FROM (SELECT updated, (ROW_NUMBER() OVER ()) AS row
        FROM board b
        ORDER BY updated DESC)
        WHERE row = 1
    """, nativeQuery = true)
    LocalDateTime findLatestUpdated();
}
