package com.springkafkaproject.user_service.mappings;

import com.springkafkaproject.user_service.dto.requestDTO.StockRequestDTO;
import com.springkafkaproject.user_service.dto.responseDTO.StockResponseDTO;
import com.springkafkaproject.user_service.entities.Alert;
import com.springkafkaproject.user_service.entities.Stock;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StockMapper {

    // Request DTO to Entity (for creating or updating)
    @Mapping(target = "alerts", ignore = true)  // We won't include alerts for request
    Stock stockRequestDTOToStock(StockRequestDTO stockRequestDTO);

    // Entity to Response DTO (for returning the stock with alerts)
    @Mapping(target = "alertIds", source = "alerts")
    StockResponseDTO stockToStockResponseDTO(Stock stock);

    @IterableMapping(elementTargetType = StockResponseDTO.class)
    List<StockResponseDTO> stockListtoStockResponseDTOList(List<Stock> stocks);

    default List<Long> mapAlertsToAlertIds(List<Alert> alerts) {
        return alerts.stream()
                .map(Alert::getId)
                .collect(Collectors.toList());
    }
}

