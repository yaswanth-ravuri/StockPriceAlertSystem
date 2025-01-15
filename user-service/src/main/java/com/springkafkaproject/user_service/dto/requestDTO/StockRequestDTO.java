package com.springkafkaproject.user_service.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockRequestDTO {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;
}
