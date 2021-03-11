package com.example.demo.controller;

import com.example.demo.model.Office;
import com.example.demo.model.Response;
import com.example.demo.service.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("offices")
public class OfficeController {

    @Autowired
    private IOfficeService officeService;

    @GetMapping
    public Response getAllOffice(){
        Iterable<Office> offices = officeService.findAll();
        Response response = new Response();
        response.setStatus(200);
        response.setMessage("Get all success");
        response.setData(offices);
        return response;
    }

    @PostMapping
    @ResponseBody
    public Response addOffice(@RequestBody Office office){
        officeService.save(office);
        Response response = new Response();
        response.setStatus(200);
        response.setMessage("Save success");
        response.setData(office);
        return response;
    }
}
