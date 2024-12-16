package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.dao.CategoryRelation;
import com.bankrupt.bankruptapi.dao.CategoryResource;
import com.bankrupt.bankruptapi.repository.CategoryRelationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryRelationService {
    private final CategoryRelationRepository categoryRelationRepository;

    private final CategoryResourceService categoryResourceService;
    private final PdfboxService pdfboxService;
    private final HwpLibService hwpLibService;

    public void saveAllCategoryRelationByBoard(Board board) {
        List<CategoryResource> allCategoryResources = categoryResourceService.findAllCategoryResources();

        Map<Long, List<CategoryResource>> categoryResourceMapByCategoryId = allCategoryResources.stream()
                .collect(Collectors.groupingBy(CategoryResource::getCategoryId));

        categoryResourceMapByCategoryId.entrySet().stream().parallel()
                .forEach(entry -> saveCategoryRelation(entry, board));
    }

    public void deleteAllCategoryRelationByBoardIdList(List<Long> boardIdList) {
        categoryRelationRepository.deleteAllByBoardIdIn(boardIdList);
    }

    public void deleteAllCategoryRelationByCategoryId(Long categoryId) {
        categoryRelationRepository.deleteAllByCategoryId(categoryId);
    }

    private void saveCategoryRelation(Map.Entry<Long, List<CategoryResource>> resourceMap, Board board) {
        Long categoryId = resourceMap.getKey();
        List<CategoryResource> categoryResourceList = resourceMap.getValue();

        if (categoryResourceList.stream()
                .parallel()
                .anyMatch(categoryResource -> isContainKeyword(categoryResource, board))
        ) {
            categoryRelationRepository.save(
                    CategoryRelation.builder()
                            .categoryId(categoryId)
                            .boardId(board.getId())
                            .build()
            );
        }
    }

    private boolean isContainKeyword(CategoryResource categoryResource, Board board) {
        String keyword = categoryResource.getKeyword();
        String file = board.getFile();
        String fileName = board.getFileName();

        return board.getTitle().contains(keyword)
                || pdfboxService.getPdfTextByScourtUrl(file, fileName).contains(keyword)
                || hwpLibService.getHwpTextByScourtUrl(file, fileName).contains(keyword);
    }
}
