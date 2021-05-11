package com.poc.logger.service;

import com.poc.logger.config.LogTimer;
import com.poc.logger.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @LogTimer
    public Employee getEmpDetails(int id){
        LOGGER.info("---------- In Service Method id :: {}", id);
        if(id==1){
            return new Employee()
                    .setId(id)
                    .setDept("Development")
                    .setName("Pk")
                    .setSalary(100.00);
        } else {
            return null;
        }
    }
}
