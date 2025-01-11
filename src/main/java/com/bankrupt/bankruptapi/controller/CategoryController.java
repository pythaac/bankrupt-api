package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController(value = "/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> findAllCategories() {
        return categoryService.findAllCategory();
    }

    @PostMapping
    public void createCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
    }

    @PutMapping(value = "/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping(value = "/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
