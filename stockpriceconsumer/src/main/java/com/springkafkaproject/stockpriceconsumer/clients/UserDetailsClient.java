package com.springkafkaproject.stockpriceconsumer.clients;

import com.springkafkaproject.dto.Alert;
import com.springkafkaproject.dto.Stock;
import com.springkafkaproject.dto.StockResponse;
import com.springkafkaproject.dto.User;
import jakarta.ws.rs.QueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "USER-SERVICE")
public interface UserDetailsClient {

    @GetMapping("/stock/getStock/{symbol}")
    StockResponse getStockandStockAlerts(@PathVariable String symbol);

    @GetMapping("/alert/getAlert/{alertId}")
    Alert getAlertwithId(@PathVariable Long alertId);

    @GetMapping("/user/getuser")
    User getUserwithId(@RequestParam("id") Long id);
}
