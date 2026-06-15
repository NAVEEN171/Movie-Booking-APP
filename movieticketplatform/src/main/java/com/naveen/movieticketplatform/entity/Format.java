package com.naveen.movieticketplatform.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "formats")
public class Format extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
