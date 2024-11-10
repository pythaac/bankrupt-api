package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.dao.CategoryRelation;
import com.bankrupt.bankruptapi.repository.CategoryRelationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CategoryRelationService {
    private final CategoryRelationRepository categoryRelationRepository;

    private final CategoryRowService categoryRowService;

    public void saveAllCategoryRelationByBoard(Board board) {
        Map<Long, List<String>> allCategoryRows = categoryRowService.getAllCategoryRow();

        allCategoryRows.entrySet().stream().parallel()
                .forEach(entry -> saveCategoryRelation(entry.getKey(), entry.getValue(), board));
    }

    public void deleteAllCategoryRelationByBoardIdList(List<Long> boardIdList) {
        categoryRelationRepository.deleteAllByBoardIdIn(boardIdList);
    }

    private void saveCategoryRelation(Long categoryId, List<String> categoryList, Board board) {
        if (categoryList.stream()
                .parallel()
                .anyMatch(category -> isMatched(category, board))
        ) {
            categoryRelationRepository.save(
                    CategoryRelation.builder()
                            .categoryId(categoryId)
                            .boardId(board.getId())
                            .build()
            );
        }
    }

    private boolean isMatched(String category, Board board) {
        return board.getTitle().contains(category);
    }
}
