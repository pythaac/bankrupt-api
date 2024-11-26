package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryResourceService categoryResourceService;
    private final CategoryRelationService categoryRelationService;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(Long id, Category category) {
        Category foundCategory = categoryRepository.findById(id).orElseThrow();
        if (foundCategory != null) {
            categoryRepository.save(category);
        }
    }

    public List<Category> findAllCategoryByBoardId(Long boardId) {
        return categoryRepository.findAllCateogryByBoardId(boardId);
    }

    public void deleteCategory(Long id) {
        categoryResourceService.deleteAllCategoryResourceByCategoryId(id);
        categoryRelationService.deleteAllCategoryRelationByCategoryId(id);
        categoryRepository.deleteById(id);
    }
}
