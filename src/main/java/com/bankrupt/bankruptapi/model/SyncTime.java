package com.bankrupt.bankruptapi.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SyncTime {
    LocalDateTime syncTime;
}
