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

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategoryName(Long id, String categoryName) {
        Category foundCategory = categoryRepository.findById(id).orElseThrow();
        foundCategory.setCategoryName(categoryName);
        categoryRepository.save(foundCategory);
    }

    public List<Category> findAllCategoryByBoardId(Long boardId) {
        return categoryRepository.findAllCateogryByBoardId(boardId);
    }
}
