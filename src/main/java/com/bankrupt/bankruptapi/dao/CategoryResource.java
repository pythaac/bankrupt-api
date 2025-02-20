package com.bankrupt.bankruptapi.dao;

import com.bankrupt.bankruptapi.dto.CategoryResourceDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "category_resource")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long categoryId;
    private String keyword;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    public static CategoryResource of(CategoryResourceDto dto) {
        return CategoryResource.builder()
                .id(dto.getCategoryResourceId())
                .categoryId(dto.getCategoryId())
                .keyword(dto.getKeyword())
                .build();
    }
}
