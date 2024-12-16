package com.bankrupt.bankruptapi.model;

import com.bankrupt.bankruptapi.dao.Category;
import com.bankrupt.bankruptapi.dao.CategoryResource;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryBundle {
    private Category category;
    private List<CategoryResource> categoryResources;
}
