package com.springkafkaproject.stockpriceproducer.services;


import com.springkafkaproject.dto.Stock;
import com.springkafkaproject.dto.StockResponseDTO;
import com.springkafkaproject.stockpriceproducer.clients.UserDetailsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsClient userDetailsClient;

    public List<StockResponseDTO> getListofStockstoTrack(){
        return userDetailsClient.getStockstoTrack();
    }
}
