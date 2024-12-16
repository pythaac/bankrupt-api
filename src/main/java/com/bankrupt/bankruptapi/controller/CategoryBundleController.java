package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.model.CategoryBundle;
import com.bankrupt.bankruptapi.service.CategoryBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController(value = "/v1/category")
public class CategoryBundleController {
    private final CategoryBundleService categoryBundleService;

    @GetMapping(value = "/bundle")
    public List<CategoryBundle> getAllCategoryBundles() {
        return categoryBundleService.getAllCategoryBundles();
    }

    @GetMapping(value = "/{categoryId}/bundle")
    public CategoryBundle getCategoryResourceByCategoryId(@PathVariable Long categoryId) {
        return categoryBundleService.getCategoryBundle(categoryId);
    }
}
