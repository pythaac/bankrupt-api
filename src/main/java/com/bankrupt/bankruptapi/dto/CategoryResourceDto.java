package com.bankrupt.bankruptapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryResourceDto {
    private Long categoryId;
    private String keyword;
}
