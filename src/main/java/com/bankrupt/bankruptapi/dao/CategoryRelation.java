package com.bankrupt.bankruptapi.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "category_relation")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long boardId;
    private Long categoryId;
    private Timestamp created;
    private Timestamp updated;
}
