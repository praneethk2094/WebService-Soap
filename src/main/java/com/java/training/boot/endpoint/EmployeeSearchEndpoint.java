package com.java.training.boot.endpoint;


import com.java.training.boot.jaxb.schema.rq.EmployeeSearchRQ;
import com.java.training.boot.jaxb.schema.common.Employee;
import com.java.training.boot.jaxb.schema.rs.EmployeeSearchRS;
import com.java.training.boot.jaxb.schema.common.Employees;
import com.java.training.boot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;

/**
 * Created by atp1pak on 7/18/2016.
 */
@Endpoint
public class EmployeeSearchEndpoint {
    @Autowired
    private EmployeeService employeeService;

    @PayloadRoot(namespace = "http://www.iTech.com/employee/portal", localPart = "EmployeeSearchRQ")
    @ResponsePayload
    public EmployeeSearchRS searchEmployee(final @RequestPayload EmployeeSearchRQ employeeSearchRQ) {
        EmployeeSearchRS result = new EmployeeSearchRS();
        Employees employees = new Employees();
         if (employeeSearchRQ.getId().isEmpty()) {
            employees.getEmployee().addAll(employeeService.findEmployees());
        } else {
            for (BigInteger id : employeeSearchRQ.getId()) {
            	if (id.intValue() > 100) {
            		throw new RuntimeException("Sorry, id can't be more than 100");
            	}
                Employee employee = employeeService.findEmployee(id.intValue());
                employees.getEmployee().add(employee);
            }
        }

        result.setEmployees(employees);

        return result;
    }
}
