package com.springkafkaproject.user_service.service;

import com.springkafkaproject.user_service.repositories.AlertRepository;
import com.springkafkaproject.user_service.entities.Alert;
import com.springkafkaproject.user_service.entities.Stock;
import com.springkafkaproject.user_service.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    Logger logger = LoggerFactory.getLogger(AlertService.class);

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private StockService stockService;


    //sends alert to user email when stock price >= alertprice
    public Pair<Boolean, String> createAlert(Long id, Double alertPrice, String stockId){
        User user = userService.getUser(id);
        Stock stock = stockService.getStock(stockId);
        if(user == null || stock == null)
            return Pair.of(false, "Error while setting stock price, stock or user doesn't match");

        Alert alert = new Alert(alertPrice, stock, user);
        try{
            alertRepository.save(alert);
            return Pair.of(true, "Alert created succesfully");
        } catch (Exception e){
            return Pair.of(false, "Error while creating alert");
        }
    }

    public Pair<Boolean, String> updateAlert(Long id, Double newAlertPrice, String stockName){
        User user = userService.getUser(id);
        Stock stock = stockService.getStock(stockName);
        if(user == null || stock == null)
            return Pair.of(false, "Error while setting stock price, stock or user doesn't match");

        Alert alert = alertRepository.findByStockAndUser(stock, user);
        try{
            alert.setPrice(newAlertPrice);
            alertRepository.save(alert);
            return Pair.of(true, "Alert updated succesfully");
        } catch (Exception e){
            return Pair.of(false, "Error while creating alert");
        }
    }

    public Pair<Boolean, String> removeAlert(Long id, String stockSymbol){
        User user = userService.getUser(id);
        Stock stock = stockService.getStock(stockSymbol);
        if(user == null || stock == null)
            return Pair.of(false, "user or stock not available");
        for(Alert alert: user.getAlerts()){
            if(alert.getStock().getSymbol().equals(stockSymbol)){
                alertRepository.delete(alert);
                return Pair.of(true, "Removed alert successfully for stock: "+stockSymbol);
            }
        }
        return Pair.of(false, "Alert not available for the stock");
    }

    public List<Alert> getAllAlertsForUser(Long id){
        User user = userService.getUser(id);
        if(user!=null){
            return user.getAlerts();
        }
        return new ArrayList<>();
    }

    public Alert getAlertwithId(Long id){
        Optional<Alert> optionalAlert = alertRepository.findById(id);
        return optionalAlert.orElse(null);
    }

}


//        List<Alert> alertList = user.getAlerts();
//
//        //if alert not present for the stock
//        if(alertList==null){
//            List<Alert> alerts = new ArrayList<>();
//            Alert alert = new Alert(stock, alertPrice, user);
//            alerts.add(alert);
//            user.setAlerts(alerts);
//            alertRepository.save(alert); //try doing alertRepository.save(alert); - works
//            return Pair.of(true, "created Alert succesfully");
//        }
//
//        //if already alert present for the stock
//        for(Alert alert: alertList){
//            if(alert.getStock().getName().equals(stock.getName())){
//                alert.setPrice(alertPrice);
//                logger.info("alert has been updated for stock: "+stockName + " for user: "+user.getName()+" with price: "+alertPrice);
//                userRepository.save(user); //try doing alertRepository.save(alert); - works
//                return Pair.of(true, "succesffuly updated the alert price for stock: "+stockName+" with price:"+alertPrice);
//            }
//        }