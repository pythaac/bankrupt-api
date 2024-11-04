package com.bankrupt.bankruptapi.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "category_relation")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long boardId;
    Long categoryId;
}
