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
}
