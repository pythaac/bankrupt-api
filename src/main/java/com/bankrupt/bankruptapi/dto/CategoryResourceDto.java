package com.bankrupt.bankruptapi.dto;

import com.bankrupt.bankruptapi.dao.CategoryResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryResourceDto {
    private Long categoryResourceId;
    private Long categoryId;
    private String keyword;

    public static CategoryResourceDto of(CategoryResource categoryResource) {
        return CategoryResourceDto.builder()
                .categoryResourceId(categoryResource.getId())
                .categoryId(categoryResource.getCategoryId())
                .keyword(categoryResource.getKeyword())
                .build();
    }
}
