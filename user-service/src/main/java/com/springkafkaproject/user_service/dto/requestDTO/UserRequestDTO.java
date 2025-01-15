package com.springkafkaproject.user_service.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springkafkaproject.user_service.entities.Alert;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;
}
