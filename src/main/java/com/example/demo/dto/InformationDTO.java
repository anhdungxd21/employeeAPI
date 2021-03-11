package com.example.demo.dto;

import com.example.demo.model.Office;
import com.example.demo.model.Position;
import lombok.Data;


@Data
public class InformationDTO {
    private Iterable<Position> position;
    private Iterable<Office> office;
}
