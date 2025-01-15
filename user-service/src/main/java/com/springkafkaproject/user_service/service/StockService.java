package com.springkafkaproject.user_service.service;


import com.springkafkaproject.user_service.repositories.StockRepository;
import com.springkafkaproject.user_service.entities.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    Logger logger = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    public String createStock(Stock newStock){
        try{
            Stock stock = getStock(newStock.getSymbol());
            if(stock==null){
                stockRepository.save(newStock);
                return "stock created / already present";
            } else throw new Exception();
        }catch (Exception e){
            logger.error("error while creating stock"+ e.getMessage());
            return "error while creating stock";
        }
    }

    public Stock getStock(String stockSymbol){
        Optional<Stock> stock = stockRepository.findById(stockSymbol);
        return stock.orElse(null);
    }

    public List<Stock> getAllAvailableStocks(){
        return stockRepository.findAll();
    }

    public String removeStock(String stockSymbol){
        Stock stock = getStock(stockSymbol);
        if(stock!=null){
            stockRepository.delete(stock);
            return "removed stock successfully";
        }
        return "not able to delete stock";
    }

}
