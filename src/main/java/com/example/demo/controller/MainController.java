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

    @GetMapping("/search")
    public Response getAllEmployeeByName(@RequestParam("name")String nameSearch){
        Integer page = 0;
        Integer size = 10;
        Response response = new Response();
        Pageable pageable = PageRequest.of(page,size);
        response.setData(employeeService.findAllByName(nameSearch,pageable));
        return response;
    }

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

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = converterDTO.getEmployee(employeeDTO);
        employeeService.save(employee);
        return new ResponseEntity(employee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Response getEmployeeById(@PathVariable("id") Long id){
        Optional optional = employeeService.findById(id);
        Response response = new Response();
        if(optional.isPresent()) {
            response.setStatus(200);
            response.setData(optional.get());
            return response;
        }else{
            response.setStatus(404);
            return response;
        }
    }

    @PutMapping("/{id}")
    public Response editEmployee(@PathVariable("id") Long id,
                                 @RequestBody EmployeeDTO employeeDTO){
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        Response response = new Response();
        if(optionalEmployee.isPresent()){
            if(employeeDTO.getPosition()==null || employeeDTO.getOffice() == null){
                response.setStatus(404);
                response.setMessage("Not Found");
                return response;
            }
            Employee employee = converterDTO.getEmployee(employeeDTO);
            employee.setId(id);
            employeeService.save(employee);
            response.setStatus(202);
            response.setMessage("Edit Employee");
            response.setData(employee);
            return response;
        }else{
            response.setStatus(404);
            response.setMessage("Not Found");
            return response;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteEmployee(@PathVariable("id")Long id){
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        Response response = new Response();
        if(optionalEmployee.isPresent()){
            employeeService.remove(id);
            response.setStatus(204);
            response.setMessage("No content");
            return response;
        }else{
            response.setStatus(404);
            response.setMessage("Not Found");
            return response;
        }
    }
}
