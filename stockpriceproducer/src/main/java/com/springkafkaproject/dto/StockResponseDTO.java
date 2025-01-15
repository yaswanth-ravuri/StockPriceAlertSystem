package com.springkafkaproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDTO {
    private String symbol;
    private String name;
    private List<Long> alertIds;
}
