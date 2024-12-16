package com.bankrupt.bankruptapi.model;

import com.bankrupt.bankruptapi.dao.CategoryResource;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CategoryBundle {
    private Long categoryId;
    private String categoryName;
    private List<CategoryResource> categoryResources;
}
