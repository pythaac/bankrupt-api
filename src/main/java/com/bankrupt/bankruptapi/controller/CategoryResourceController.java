package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.dao.CategoryResource;
import com.bankrupt.bankruptapi.service.CategoryResourceService;
import com.bankrupt.bankruptapi.service.CategoryService;
import com.bankrupt.bankruptapi.service.ScourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController(value = "/v1/category/resource")
public class CategoryResourceController {
    private final CategoryResourceService categoryResourceService;

    @PostMapping
    public void createCategoryResource(@RequestBody CategoryResource categoryResource) {
        categoryResourceService.saveCategoryResource(categoryResource);
    }

    @PutMapping(value = "/{categoryResourceId}")
    public void updateCategoryResource(
            @PathVariable Long categoryResourceId,
            @RequestBody CategoryResource categoryResource) {
        categoryResourceService.updateCategoryResourceCategory(categoryResourceId, categoryResource);
    }

    @DeleteMapping(value = "/{categoryResourceId}")
    public void deleteCategoryResource(@PathVariable Long categoryResourceId) {
        categoryResourceService.deleteCategoryResourceById(categoryResourceId);
    }
}
