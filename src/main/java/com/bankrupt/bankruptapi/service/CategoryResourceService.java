package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.CategoryResource;
import com.bankrupt.bankruptapi.repository.CategoryRepository;
import com.bankrupt.bankruptapi.repository.CategoryResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryResourceService {
    private final CategoryResourceRepository categoryResourceRepository;

    public void saveCategoryResource(CategoryResource categoryResource) {
        categoryResourceRepository.save(categoryResource);
    }

    public void updateCategoryResourceCategory(Long id, String category) {
        CategoryResource foundCategoryResource = categoryResourceRepository.findById(id).orElseThrow();
        foundCategoryResource.setCategory(category);
        categoryResourceRepository.save(foundCategoryResource);
    }

    public void deleteCategoryResourceById(Long id) {
        categoryResourceRepository.deleteById(id);
    }

    public void deleteAllCategoryResourceByCategoryId(Long categoryId) {
        categoryResourceRepository.deleteAllByCategoryId(categoryId);
    }
}
