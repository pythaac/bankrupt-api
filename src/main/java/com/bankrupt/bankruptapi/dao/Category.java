package com.bankrupt.bankruptapi.dao;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "category")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String categoryName;
}
