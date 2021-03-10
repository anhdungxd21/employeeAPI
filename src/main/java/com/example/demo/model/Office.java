package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "offices")
@Data
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
