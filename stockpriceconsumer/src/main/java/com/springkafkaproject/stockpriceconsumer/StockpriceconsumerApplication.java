package com.springkafkaproject.stockpriceconsumer;

import com.springkafkaproject.stockpriceconsumer.services.StockPriceConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StockpriceconsumerApplication{

	@Autowired
	private StockPriceConsumerService stockPriceConsumerService;

	public static void main(String[] args) {
		SpringApplication.run(StockpriceconsumerApplication.class, args);
	}

}
