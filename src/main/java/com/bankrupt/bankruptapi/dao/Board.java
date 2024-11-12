package com.bankrupt.bankruptapi.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "board")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Board {
    @Id
    private Long id;
    private String seller;
    private String court;
    private String title;
    private String created;
    private String due;
    private String file;
    private String fileName;
    private String telephoneNumber;
}
