package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.dao.CategoryResource;
import com.bankrupt.bankruptapi.dto.CategoryResourceDto;
import com.bankrupt.bankruptapi.repository.BoardRepository;
import com.bankrupt.bankruptapi.repository.CategoryResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CategoryResourceService {
    private final CategoryResourceRepository categoryResourceRepository;
    private final BoardRepository boardRepository;

    private final CategoryRelationService categoryRelationService;

    public List<CategoryResource> findAllCategoryResources() {
        return categoryResourceRepository.findAll();
    }

    public List<CategoryResource> findAllCategoryResourcesByCategoryId(Long categoryId) {
        return categoryResourceRepository.findAllByCategoryId(categoryId);
    }

    @Transactional
    public void saveCategoryResource(CategoryResourceDto categoryResourceDto) {
        CategoryResource categoryResource = CategoryResource.of(categoryResourceDto);
        categoryResourceRepository.save(categoryResource);

        Long categoryId = categoryResourceDto.getCategoryId();
        saveAllCategoryRelationByCategoryId(categoryId);
    }

    @Transactional
    public void updateCategoryResourceCategory(Long id, CategoryResourceDto categoryResource) {
        CategoryResource foundCategoryResource = categoryResourceRepository.findById(id).orElseThrow();
        foundCategoryResource.setKeyword(categoryResource.getKeyword());
        categoryResourceRepository.save(foundCategoryResource);

        Long categoryId = categoryResource.getCategoryId();
        updateAllCategoryRelationByCategoryId(categoryId);
    }

    @Transactional
    public void deleteCategoryResourceById(Long id) {
        CategoryResource categoryResource = categoryResourceRepository.findById(id).orElseThrow();
        categoryResourceRepository.deleteById(id);

        Long categoryId = categoryResource.getCategoryId();
        updateAllCategoryRelationByCategoryId(categoryId);
    }

    public void deleteAllCategoryResourceByCategoryId(Long categoryId) {
        categoryResourceRepository.deleteAllByCategoryId(categoryId);
    }

    private void saveAllCategoryRelationByCategoryId(Long categoryId) {
        List<CategoryResource> allCategoryResourcesByCategoryId = findAllCategoryResourcesByCategoryId(categoryId);

        Map.Entry<Long, List<CategoryResource>> entry =
                new AbstractMap.SimpleEntry<>(categoryId, allCategoryResourcesByCategoryId);

        List<Board> boards = boardRepository.findAll();

        boards.stream().parallel()
                .forEach(board -> categoryRelationService.saveCategoryRelation(entry, board));
    }

    private void updateAllCategoryRelationByCategoryId(Long categoryId) {
        List<CategoryResource> allCategoryResourcesByCategoryId = findAllCategoryResourcesByCategoryId(categoryId);

        Map.Entry<Long, List<CategoryResource>> entry =
                new AbstractMap.SimpleEntry<>(categoryId, allCategoryResourcesByCategoryId);

        List<Board> boards = boardRepository.findAll();

        boards.stream().parallel()
                .forEach(board -> categoryRelationService.updateCategoryRelation(entry, board));
    }
}
