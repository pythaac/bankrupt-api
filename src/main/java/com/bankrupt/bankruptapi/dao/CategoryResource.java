package com.bankrupt.bankruptapi.dao;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Table(name = "category_resource")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long categoryId;
    String category;
}
