package com.springkafkaproject.stockpriceconsumer.services;


import com.springkafkaproject.dto.User;
import com.springkafkaproject.stockpriceconsumer.clients.UserDetailsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private UserDetailsClient userDetailsClient;

    public void sendEmailNotification(Long userId, String stock, Double price){
        User user = userDetailsClient.getUserwithId(userId);
        logger.warn("User Alerted with: "+ " Dear User "+user.getName()+
                ", \n this is to notify that an alert is triggered for stock "+ stock+" as it reached below the alert price of "+price);
    }
}
