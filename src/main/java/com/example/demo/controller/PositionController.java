package com.example.demo.controller;

import com.example.demo.model.Position;
import com.example.demo.model.Response;
import com.example.demo.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("positions")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @GetMapping
    public Response findAllPosition(){
        Response response = new Response();
        Iterable<Position> positions = positionService.findAll();
        response.setData(positions);
        response.setMessage("Success");
        response.setStatus(200);
        return response;
    }

    @PostMapping
    @ResponseBody
    public Response addPosition(@RequestBody Position position){
        positionService.save(position);
        Response response = new Response();
        response.setData(position);
        response.setMessage("Success");
        response.setStatus(200);
        return response;
    }
}
