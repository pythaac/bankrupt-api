package com.bankrupt.bankruptapi.model;

import com.bankrupt.bankruptapi.dto.CategoryDto;
import com.bankrupt.bankruptapi.dto.CategoryResourceDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryBundle {
    private CategoryDto category;
    private List<CategoryResourceDto> categoryResources;
}
