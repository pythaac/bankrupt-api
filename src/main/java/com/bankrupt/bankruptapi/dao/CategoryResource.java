package com.bankrupt.bankruptapi.dao;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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
    private String category;
    private Timestamp created;
    private Timestamp updated;
}
