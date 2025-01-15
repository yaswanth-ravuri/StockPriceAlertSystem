package com.springkafkaproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {
    private String symbol;
    private String name;
    private List<Long> alertIds;
}
