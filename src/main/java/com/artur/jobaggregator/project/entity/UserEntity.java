package com.artur.jobaggregator.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;


}
