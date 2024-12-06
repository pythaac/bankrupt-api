package com.bankrupt.bankruptapi.dao;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

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
    private Timestamp created;
    private Timestamp updated;
}
