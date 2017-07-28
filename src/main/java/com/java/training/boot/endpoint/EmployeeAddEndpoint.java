package com.java.training.boot.endpoint;

import com.java.training.boot.jaxb.schema.common.Employee;
import com.java.training.boot.jaxb.schema.common.Employees;
import com.java.training.boot.jaxb.schema.rq.EmployeeAddRQ;
import com.java.training.boot.jaxb.schema.rs.EmployeeAddRS;
import com.java.training.boot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by atp1pak on 7/19/2016.
 */
@Endpoint
public class EmployeeAddEndpoint {
    @Autowired
    private EmployeeService employeeService;

    @PayloadRoot(namespace = "http://www.iTech.com/employee/portal", localPart = "EmployeeAddRQ")
    @ResponsePayload
    public EmployeeAddRS addEmployee(final @RequestPayload EmployeeAddRQ employeeAddRQ) {
        EmployeeAddRS result = new EmployeeAddRS();
        StringBuilder sb  = new StringBuilder();
        System.out.println("Employees Size = " + employeeAddRQ.getEmployees().getEmployee().size());
        Employees employees = new Employees();
        for (Employee emp : employeeAddRQ.getEmployees().getEmployee()) {
            if (employeeService.findEmployee(emp.getId()) != null) {
                sb.append("ID ").append(emp.getId());
            } else {
                employees.getEmployee().add(emp);
            }
        }
        if (!employees.getEmployee().isEmpty())
            employeeService.addEmployee(employees);

        String message = "New Employees Added successfully! ";
        if (employees.getEmployee().isEmpty()) {
            message += "Found Duplicates = " + sb.toString();
        }
        result.setMessage(message);
        return result;
    }
}
