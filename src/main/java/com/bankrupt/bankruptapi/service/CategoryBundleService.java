package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.dao.CategoryResource;
import com.bankrupt.bankruptapi.dto.CategoryDto;
import com.bankrupt.bankruptapi.dto.CategoryResourceDto;
import com.bankrupt.bankruptapi.model.CategoryBundle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryBundleService {
    private final CategoryService categoryService;
    private final CategoryResourceService categoryResourceService;

    public List<CategoryBundle> getAllCategoryBundles() {
        List<CategoryDto> allCategory = categoryService.findAllCategory();

        return allCategory.stream()
                .map(category -> getCategoryBundle(category.getId()))
                .toList();
    }

    public CategoryBundle getCategoryBundle(Long categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        List<CategoryResource> categoryResources =
                categoryResourceService.findAllCategoryResourcesByCategoryId(categoryId);

        return CategoryBundle.builder()
                .category(CategoryDto.of(category))
                .categoryResources(categoryResources.stream().map(CategoryResourceDto::of).toList())
                .build();
    }
}
