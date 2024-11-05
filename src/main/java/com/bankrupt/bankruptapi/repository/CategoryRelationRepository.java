package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.CategoryRelation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRelationRepository extends JpaRepository<CategoryRelation, Long> {

    @Transactional
    void deleteAllByBoardIdIn(List<Long> boardIdList);
}
