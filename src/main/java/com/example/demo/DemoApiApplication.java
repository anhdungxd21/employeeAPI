package com.example.demo;

import com.example.demo.service.IPositionService;
import com.example.demo.service.impl.PositionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApiApplication.class, args);
    }
}
