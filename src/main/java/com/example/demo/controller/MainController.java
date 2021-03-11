package com.example.demo.controller;

import com.example.demo.dto.ConverterDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.InformationDTO;
import com.example.demo.model.*;
import com.example.demo.service.IEmployeeService;
import com.example.demo.service.IOfficeService;
import com.example.demo.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("employees")
public class MainController {
    @Autowired
    private ConverterDTO converterDTO;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IOfficeService officeService;
    @Autowired
    private IPositionService positionService;

    @GetMapping
    public Response getAllPaging(@RequestParam("page")Optional<String> optionalPage, @RequestParam("size") Optional<String> optionalSize){
        Integer page = 0;
        Integer size = 10;
        if(optionalPage.isPresent()){
            page = Integer.parseInt(optionalPage.get());
        }
        if(optionalSize.isPresent()){
            size = Integer.parseInt(optionalSize.get());
        }
        Response response = new Response();
        Pageable pageable = PageRequest.of(page, size);
        response.setData(employeeService.findAll( pageable));

        return response;
    }

    @GetMapping("/create")
    public Response getInfoCreate(){
        InformationDTO info = new InformationDTO();
        info.setPosition(positionService.findAll());
        info.setOffice(officeService.findAll());
        return new Response(200,"Information",info);
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = converterDTO.getEmployee(employeeDTO);
        employeeService.save(employee);
        return new ResponseEntity(employee, HttpStatus.CREATED);
    }

}
