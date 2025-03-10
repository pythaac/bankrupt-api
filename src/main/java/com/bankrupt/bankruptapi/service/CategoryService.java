package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.dto.CategoryDto;
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

    public List<CategoryDto> findAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryDto::of).toList();
    }

    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    public List<Category> findAllCategoryByBoardId(Long boardId) {
        return categoryRepository.findAllCateogryByBoardId(boardId);
    }

    public void saveCategory(CategoryDto categoryDto) {
        categoryRepository.save(Category.of(categoryDto));
    }

    @Transactional
    public void updateCategory(Long id, CategoryDto category) {
        Category foundCategory = categoryRepository.findById(id).orElseThrow();

        String categoryName = category.getCategoryName();
        foundCategory.setCategoryName(categoryName);
        categoryRepository.save(foundCategory);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryResourceService.deleteAllCategoryResourceByCategoryId(id);
        categoryRelationService.deleteAllCategoryRelationByCategoryId(id);
        categoryRepository.deleteById(id);
    }
}
