package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.dao.CategoryRelation;
import com.bankrupt.bankruptapi.repository.CategoryRelationRepository;
import com.bankrupt.bankruptapi.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CategoryRelationService {
    private final CategoryRelationRepository categoryRelationRepository;

    private final CategoryService categoryService;

    public void saveCategoryRelationsByBoard(Board board) {
        Map<Long, List<String>> allCategoryRows = categoryService.getAllCategoryRows();
        allCategoryRows.forEach((key, value) -> saveCategoryRelation(key, value, board));
    }

    private void saveCategoryRelation(Long categoryId, List<String> categoryList, Board board) {
        if (categoryList.stream().anyMatch(category -> board.getTitle().contains(category))) {
            categoryRelationRepository.save(
                    CategoryRelation.builder()
                            .categoryId(categoryId)
                            .boardId(board.getId())
                            .build()
            );
        }
    }
}
