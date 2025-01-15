package com.springkafkaproject.user_service.controller;


import com.springkafkaproject.user_service.dto.requestDTO.AlertRequestDTO;
import com.springkafkaproject.user_service.dto.responseDTO.AlertResponseDTO;
import com.springkafkaproject.user_service.entities.Alert;
import com.springkafkaproject.user_service.mappings.AlertMapper;
import com.springkafkaproject.user_service.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertMapper alertMapper;

    @GetMapping("/getAllAlertsForUser")
    public List<AlertResponseDTO> getAllAlertsForUser(@RequestParam("id") Long id){
        List<Alert> alerts = alertService.getAllAlertsForUser(id);
        List<AlertResponseDTO> alertResponseDTOS = new ArrayList<>();
        for(Alert alert: alerts){
            alertResponseDTOS.add(alertMapper.alertToAlertResponseDTO(alert));
        }
        return alertResponseDTOS;
    }

    @GetMapping("/getAlert/{alertId}")
    public AlertResponseDTO getAlert(@PathVariable("alertId") Long alertId){
        Alert alert = alertService.getAlertwithId(alertId);
        return alert!=null ? alertMapper.alertToAlertResponseDTO(alert) : null;
    }

    @PostMapping("/createAlert")
    public ResponseEntity<String> createAlert(@RequestBody AlertRequestDTO alertRequest){
        Pair<Boolean, String> pair = alertService.createAlert(alertRequest.getUserId(), alertRequest.getPrice(), alertRequest.getStockSymbol());
        if(pair.getFirst())
            return new ResponseEntity(pair.getSecond(), HttpStatus.CREATED);
        return new ResponseEntity(pair.getSecond(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateAlert")
    public ResponseEntity<String> updateAlert(@RequestBody AlertRequestDTO alertRequest){
        Pair<Boolean, String> pair = alertService.updateAlert(alertRequest.getUserId(), alertRequest.getPrice(), alertRequest.getStockSymbol());
        if(pair.getFirst())
            return new ResponseEntity(pair.getSecond(), HttpStatus.OK);
        return new ResponseEntity(pair.getSecond(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/removeAlert")
    public ResponseEntity<String> removeAlertForUserandStock(@RequestParam("userId") Long id, @RequestParam("stockSymbol") String stockSymbol){
        Pair<Boolean, String> pair = alertService.removeAlert(id, stockSymbol);
        if(pair.getFirst())
            return new ResponseEntity<>(pair.getSecond(), HttpStatus.OK);
        return new ResponseEntity<>(pair.getSecond(), HttpStatus.BAD_REQUEST);
    }
}
