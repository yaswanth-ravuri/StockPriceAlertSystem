package com.springkafkaproject.user_service.mappings;

import com.springkafkaproject.user_service.dto.requestDTO.AlertRequestDTO;
import com.springkafkaproject.user_service.dto.responseDTO.AlertResponseDTO;
import com.springkafkaproject.user_service.entities.Alert;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlertMapper {

    // Request DTO to Entity (for creating or updating)
    @Mapping(target = "stock.symbol", source = "stockSymbol")
    @Mapping(target = "user.id", source = "userId")
    Alert alertRequestDTOToAlert(AlertRequestDTO alertRequestDTO);

    // Entity to Response DTO (for returning the alert with stock and user details)
    @Mapping(target = "stockSymbol", source = "stock.symbol")
    @Mapping(target = "userId", source = "user.id")
    AlertResponseDTO alertToAlertResponseDTO(Alert alert);

    @IterableMapping(elementTargetType = AlertResponseDTO.class)
    List<AlertResponseDTO> alertListtoAlertResponseDTOList(List<Alert> alerts);
}

