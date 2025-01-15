package com.springkafkaproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    private Long id;
    private Double price;
    private String stockSymbol;
    private Long userId;
}
