package com.bankrupt.bankruptapi.dto;

import com.bankrupt.bankruptapi.dao.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryDto {
    private Long id;
    @NotBlank
    private String categoryName;

    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }
}
