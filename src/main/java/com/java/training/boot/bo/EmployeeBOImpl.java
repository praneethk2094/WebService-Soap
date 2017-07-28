package com.java.training.boot.bo;

import com.java.training.boot.jaxb.schema.common.Address;
import com.java.training.boot.jaxb.schema.common.Employee;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by atp1pak on 7/13/2016.
 */
@Component
public class EmployeeBOImpl implements EmployeeBO {
    private static List<Employee> employees = new ArrayList<Employee>();
    private static List<Address> addresses = new ArrayList<Address>();
    static {
        for (int i=0; i < 10; i++) {
            String idxValue = String.valueOf(i+1);
            Address address = new Address("Street " + idxValue, "Suite " + idxValue, "State " + idxValue, "ZipCode " + idxValue, "Country " + idxValue, "email" + idxValue + "@email.com",String.valueOf(11000+i));
            addresses.add(address);
            employees.add(new Employee(i+1, 30+i, "First Name " + idxValue, "Last Name " + idxValue, address));
        }
    }

    @Override
    public List<Employee> findEmployees() {
         return employees;
    }

    @Override
    public Employee findEmployee(Integer id) {
        Assert.notNull(id);
        Employee emp = new Employee(id);
        Optional<Employee> empMatched = employees.stream().filter(e->e.equals(emp)).findFirst();
        Employee empFound = null;
        if (empMatched.isPresent()) {
            empFound = empMatched.get();
        } else {
            empFound = empMatched.orElse(null);
        }
        return empFound;
    }

    @Override
    public Employee deleteEmployee(Integer id) {
        Employee emp = findEmployee(id);
        if (emp == null) {
            return emp;
        }
        employees.remove(emp);
        return emp;
    }

	@Override
	public void addEmployee(Employee inputEmployee) {
 		employees.add(inputEmployee);

	}
}
