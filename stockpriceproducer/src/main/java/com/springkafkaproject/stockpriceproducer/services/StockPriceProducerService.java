package com.springkafkaproject.stockpriceproducer.services;

import com.springkafkaproject.dto.Stock;
import com.springkafkaproject.dto.StockResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StockPriceProducerService {

    private final String topic = "stock-prices";
    private final Logger logger = LoggerFactory.getLogger(StockPriceProducerService.class);

    @Autowired
    private KafkaTemplate<String, Stock> kafkaTemplate;

    @Autowired
    private UserDetailsService userDetailsService;

    @Scheduled(fixedDelay = 6000) // Produce a new price every minute for all stocks
    public void produceStockPrices(){
        Random random = new Random();
        List<StockResponseDTO> stockList = userDetailsService.getListofStockstoTrack();
        for (StockResponseDTO stockResponse : stockList) {
            double price = 100 + (random.nextDouble() * 100);  // Dummy price generation
            Stock stock = new Stock();
            stock.setSymbol(stockResponse.getSymbol());
            stock.setName(stock.getName());
            try{
                stock.setPrice(price);
                kafkaTemplate.send(topic, stock);  // Send price to Kafka topic
                logger.info("sent price: "+price + " for stock: "+stock);
            } catch (Exception e){
                logger.error("error while sending price to kafka for stock: "+e.getMessage());
            }
        }
    }
}
