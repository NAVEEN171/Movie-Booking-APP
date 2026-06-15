package com.naveen.movieticketplatform.entity;


import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "censor_ratings")
public class CensorRating extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}