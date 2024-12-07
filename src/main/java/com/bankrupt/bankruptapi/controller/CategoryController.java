package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.dao.CategoryResource;
import com.bankrupt.bankruptapi.service.CategoryResourceService;
import com.bankrupt.bankruptapi.service.CategoryService;
import com.bankrupt.bankruptapi.service.ScourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController(value = "/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ScourtService scourtService;
    private final CategoryResourceService categoryResourceService;

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

    @GetMapping(value = "/relation")
    public String test() {
        scourtService.updateDiff();
        return "done";
    }

    @PostMapping(value = "/resource")
    public void createCategoryResource(@RequestBody CategoryResource categoryResource) {
        categoryResourceService.saveCategoryResource(categoryResource);
    }

    @PutMapping(value = "/resource/{categoryResourceId}")
    public void updateCategoryResource(
            @PathVariable int categoryResourceId,
            @RequestBody CategoryResource categoryResource) {

    }

    @GetMapping(value = "/row")
    public String test2() {
        scourtService.updateDiff();
        return "done";
    }
}
