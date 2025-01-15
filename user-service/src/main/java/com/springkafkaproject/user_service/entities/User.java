package com.springkafkaproject.user_service.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users_table")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @Column(unique = true, nullable = false)
    @NonNull
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Alert> alerts;

}
