package com.springkafkaproject.user_service.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "stocks_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    private String symbol;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stock")
    private List<Alert> alerts;
}
