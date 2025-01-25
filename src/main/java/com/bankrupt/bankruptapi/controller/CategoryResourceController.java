package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.dao.CategoryResource;
import com.bankrupt.bankruptapi.dto.CategoryResourceDto;
import com.bankrupt.bankruptapi.model.CategoryBundle;
import com.bankrupt.bankruptapi.service.CategoryBundleService;
import com.bankrupt.bankruptapi.service.CategoryResourceService;
import com.bankrupt.bankruptapi.service.CategoryService;
import com.bankrupt.bankruptapi.service.ScourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/category/resource")
public class CategoryResourceController {
    private final CategoryResourceService categoryResourceService;

    @PostMapping
    public void createCategoryResource(@RequestBody @Validated CategoryResourceDto categoryResource) {
        categoryResourceService.saveCategoryResource(categoryResource);
    }

    @PutMapping(value = "/{categoryResourceId}")
    public void updateCategoryResource(
            @PathVariable Long categoryResourceId,
            @RequestBody @Validated CategoryResourceDto categoryResource) {
        categoryResourceService.updateCategoryResourceCategory(categoryResourceId, categoryResource);
    }

    @DeleteMapping(value = "/{categoryResourceId}")
    public void deleteCategoryResource(@PathVariable Long categoryResourceId) {
        categoryResourceService.deleteCategoryResourceById(categoryResourceId);
    }
}
