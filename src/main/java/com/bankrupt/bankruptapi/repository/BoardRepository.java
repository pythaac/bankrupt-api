package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.Board;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Transactional
    @Query(value = "SELECT id FROM board", nativeQuery = true)
    List<Long> getBoardIds();
}
