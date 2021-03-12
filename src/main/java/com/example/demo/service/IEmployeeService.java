package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService extends GeneralService<Employee> {
    public Page<Employee> findAll(Pageable pageable);
    public Page<Employee> findAllByName(String name,Pageable pageable);
}
