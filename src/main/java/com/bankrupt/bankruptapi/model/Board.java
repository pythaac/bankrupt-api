package com.bankrupt.bankruptapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
    Integer id;
    String court;
    String seller;
    String title;
    String referer;
//    Integer views;
}
