package com.java.training.boot.bo;

import com.java.training.boot.jaxb.schema.common.Employee;

import java.util.List;

/**
 * Created by atp1pak on 7/13/2016.
 */
public interface EmployeeBO {
    List<Employee> findEmployees();
    Employee findEmployee(Integer id);
    Employee deleteEmployee(Integer id);
    void addEmployee(Employee inputEmployee);
}
