package com.bankrupt.bankruptapi.repository;

import com.bankrupt.bankruptapi.dao.CategoryRelation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
class CategoryRelationRepositoryTest {
    @Autowired
    private CategoryRelationRepository categoryRelationRepository;

    @Test
    void deleteAllByBoardIdIn() {
        List<CategoryRelation> insertList = List.of(
                CategoryRelation.builder()
                        .id(1000L)
                        .boardId(1000L)
                        .categoryId(1000L)
                        .build(),
                CategoryRelation.builder()
                        .id(2000L)
                        .boardId(1000L)
                        .categoryId(2000L)
                        .build(),
                CategoryRelation.builder()
                        .id(3000L)
                        .boardId(2000L)
                        .categoryId(3000L)
                        .build(),
                CategoryRelation.builder()
                        .id(4000L)
                        .boardId(3000L)
                        .categoryId(4000L)
                        .build()
        );

        categoryRelationRepository.saveAll(insertList);

        List<CategoryRelation> all = categoryRelationRepository.findAll();
        assertEquals(4, all.size());

        categoryRelationRepository.deleteAllByBoardIdIn(List.of(1000L, 2000L));

        all = categoryRelationRepository.findAll();
        assertEquals(1, all.size());

        categoryRelationRepository.deleteById(4000L);
    }
}