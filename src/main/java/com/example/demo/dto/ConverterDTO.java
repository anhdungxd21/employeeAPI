package com.example.demo.dto;

import com.example.demo.model.Employee;
import com.example.demo.service.IPositionService;
import com.example.demo.service.impl.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConverterDTO {
    private Employee employee;
    private EmployeeDTO employeeDTO;
    @Autowired
    private OfficeService officeService = new OfficeService();
    @Autowired
    private IPositionService positionService;

    public Employee getEmployee(EmployeeDTO employeeDTO){
        this.employeeDTO = employeeDTO;

        employee = new Employee();
        long milis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(milis);

        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setDate(date);

        employee.setOffice(officeService.findById(employeeDTO.getOffice()).get());
        employee.setPosition(positionService.findById(employeeDTO.getPosition()).get());

        return employee;
    }

}
