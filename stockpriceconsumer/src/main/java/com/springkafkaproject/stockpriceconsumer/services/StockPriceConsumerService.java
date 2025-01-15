package com.springkafkaproject.stockpriceconsumer.services;


import com.springkafkaproject.dto.Alert;
import com.springkafkaproject.dto.Stock;
import com.springkafkaproject.dto.StockResponse;
import com.springkafkaproject.stockpriceconsumer.clients.UserDetailsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockPriceConsumerService {

    private Logger logger = LoggerFactory.getLogger(StockPriceConsumerService.class);

    @Autowired
    private UserDetailsClient userDetailsClient;

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = {"stock-prices"}, groupId = "stock-price-consumers")
    public void processStockPrices(Stock stockEvent){
        //getStock and take list of Alert Ids for that stock
        StockResponse stock = userDetailsClient.getStockandStockAlerts(stockEvent.getSymbol());
        List<Long> alertIds = stock.getAlertIds();

        //getAlertDetails for Each alertId
        for(Long id: alertIds){
            Alert alert = userDetailsClient.getAlertwithId(id);
            //if any alert matches then get user details then send notification
            if(alert.getPrice()>=stockEvent.getPrice()){
                notificationService.sendEmailNotification(alert.getUserId(), stock.getName(), alert.getPrice());
            }
        }
    }

}
