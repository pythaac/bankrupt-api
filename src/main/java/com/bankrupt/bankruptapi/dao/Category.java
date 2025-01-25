package com.bankrupt.bankruptapi.dao;

import com.bankrupt.bankruptapi.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "category")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    public static Category of(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .categoryName(categoryDto.getCategoryName())
                .build();
    }
}
