package com.springkafkaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private String symbol;
    private String name;
    private double price;
}
