package com.java.training.boot.service;

import com.java.training.boot.bo.EmployeeBO;
import com.java.training.boot.jaxb.schema.common.Employee;
import com.java.training.boot.jaxb.schema.common.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by atp1pak on 7/13/2016.
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeBO employeeBO;

    public List<Employee> findEmployees() {
        return employeeBO.findEmployees();
    }

    public Employee findEmployee(Integer id) {
        return employeeBO.findEmployee(id);
    }

    public Employee deleteEmployee(Integer id) {
        return employeeBO.deleteEmployee(id);
    }
    
    public void addEmployee(Employees employees) {
        for (Employee employee : employees.getEmployee()) {
            System.out.println("Adding emp id = " + employee.getId());
            employeeBO.addEmployee(employee);
        }
    }
}
