package com.poc.logger.controller;

import com.poc.logger.config.LogTimer;
import com.poc.logger.dto.Employee;
import com.poc.logger.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @LogTimer
    @GetMapping(value = "/name/{id}", produces = "application/json")
    public ResponseEntity<Employee> getName(@PathVariable("id") int id){
        return new ResponseEntity<>(employeeService.getEmpDetails(id), HttpStatus.OK);
    }
}
