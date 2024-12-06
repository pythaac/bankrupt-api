package com.bankrupt.bankruptapi.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class CategoryRow {
    @Id
    private Long id;
    private String categoryName;
    private String category;
}
