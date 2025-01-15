package com.springkafkaproject.user_service.controller;

import com.springkafkaproject.user_service.dto.requestDTO.StockRequestDTO;
import com.springkafkaproject.user_service.dto.responseDTO.StockResponseDTO;
import com.springkafkaproject.user_service.entities.Stock;
import com.springkafkaproject.user_service.mappings.StockMapper;
import com.springkafkaproject.user_service.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockMapper stockMapper;

    @GetMapping("/getAllStocks")
    public List<StockResponseDTO> getAllStocks(){
        List<Stock> stocks = stockService.getAllAvailableStocks();
        return stockMapper.stockListtoStockResponseDTOList(stocks);
    }

    @GetMapping("/getStock/{symbol}")
    public StockResponseDTO getStock(@PathVariable("symbol") String symbol){
        Stock stock = stockService.getStock(symbol);
        if(stock!=null){
            return stockMapper.stockToStockResponseDTO(stock);
        }
        return null;
    }

    @PostMapping("/addStock")
    public String addStock(@RequestBody StockRequestDTO stockRequest){
        Stock stock = stockMapper.stockRequestDTOToStock(stockRequest);
        return stockService.createStock(stock);
    }

    @DeleteMapping("/deleteStock/{symbol}")
    public String deleteStock(@PathVariable("symbol") String stockSymbol){
        return stockService.removeStock(stockSymbol);
    }

}
