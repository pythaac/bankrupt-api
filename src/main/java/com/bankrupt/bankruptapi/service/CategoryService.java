package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.dao.CategoryRow;
import com.bankrupt.bankruptapi.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Map<Long, List<String>> getAllCategoryRows() {
        List<CategoryRow> allCategoryRows = categoryRepository.findAllCategoryRows();

        return allCategoryRows.stream().collect(
                Collectors.groupingBy(
                        CategoryRow::getId,
                        Collectors.mapping(CategoryRow::getCategory, Collectors.toList())
                )
        );

    }
}
