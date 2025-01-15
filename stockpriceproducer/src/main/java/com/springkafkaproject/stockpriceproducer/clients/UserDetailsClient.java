package com.springkafkaproject.stockpriceproducer.clients;

import com.springkafkaproject.dto.Stock;
import com.springkafkaproject.dto.StockResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "USER-SERVICE")
public interface UserDetailsClient {

    @GetMapping("/stock/getAllStocks")
    List<StockResponseDTO> getStockstoTrack();
}
