package com.springkafkaproject.user_service.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertRequestDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("stockSymbol")
    private String stockSymbol;

    @JsonProperty("userId")
    private Long userId;
}
