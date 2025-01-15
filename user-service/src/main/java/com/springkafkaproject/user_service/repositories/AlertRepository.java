package com.springkafkaproject.user_service.repositories;

import com.springkafkaproject.user_service.entities.Alert;
import com.springkafkaproject.user_service.entities.Stock;
import com.springkafkaproject.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    Alert findByStockAndUser(Stock stock, User user);

}
