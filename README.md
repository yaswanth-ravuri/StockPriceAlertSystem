Stock Price Alert System or App
An Application used to trigger alerts for users when stock price reached below the alert price set by the user.

Functional Requirements:
1.  Generate dummy price for every minute for all stock listed.
2.  Users able to set alerts for each stock.
3.  Users gets notified (through email) when the price reaches below the alert price set by the user.

Non-Functional Requirements:
1. Loose coupling (Achieved by using Event Driven Architecture (EDA) with Apache KAFKA)
2. Scaling and Load balance (Achieved by using Eureka as Service Registry and Ribbon as client side Load Balancer)


Architecture Flow:
1. User registers through User-service which will be stored in MysQL DB.
2. User Creates alerts for the stock required through User-Service and this data is stored in MySQL.
3. StockPriceProducer will get the list of stocks from user-service and generates Mocked/dummy price for each stock and send this as an event to Kafka
5. StockPriceConsumer will consume this price events for each stock
6. For Every stock price event, StockPriceConsumer will fetch the List of alerts set for stock from user-service.
7. StockPriceConsumer will traverse all alert prices
8. and checks if any price event for that stock is below any alert price then an email alert is triggered which will be sent to users email.

Tools and Technologies:
Java, Spring Boot, KAFKA, Spring MVC, Spring Cloud, Eureka server, Spring Feign client, Spring Data JPA, Hibernate, MySQL, Swagger UI
