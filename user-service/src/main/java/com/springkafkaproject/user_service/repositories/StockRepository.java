package com.springkafkaproject.user_service.repositories;

import com.springkafkaproject.user_service.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
//    Stock findByName(String name);

}
