package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.dao.CategoryResource;
import com.bankrupt.bankruptapi.repository.CategoryRepository;
import com.bankrupt.bankruptapi.repository.CategoryResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryResourceService {
    private final CategoryResourceRepository categoryResourceRepository;

    public List<CategoryResource> findAllCategoryResources() {
        return categoryResourceRepository.findAll();
    }

    public List<CategoryResource> findAllCategoryResourcesByCategoryId(Long categoryId) {
        return categoryResourceRepository.findAllByCategoryId(categoryId);
    }

    public void saveCategoryResource(CategoryResource categoryResource) {
        categoryResourceRepository.save(categoryResource);
    }

    public void updateCategoryResourceCategory(Long id, CategoryResource categoryResource) {
        CategoryResource foundCategoryResource = categoryResourceRepository.findById(id).orElseThrow();

        foundCategoryResource.setCategoryId(categoryResource.getCategoryId());
        foundCategoryResource.setKeyword(categoryResource.getKeyword());
        categoryResourceRepository.save(foundCategoryResource);
    }

    public void deleteCategoryResourceById(Long id) {
        categoryResourceRepository.deleteById(id);
    }

    public void deleteAllCategoryResourceByCategoryId(Long categoryId) {
        categoryResourceRepository.deleteAllByCategoryId(categoryId);
    }
}
