package com.springkafkaproject.dto;


import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private List<Long> alertIds;
}
