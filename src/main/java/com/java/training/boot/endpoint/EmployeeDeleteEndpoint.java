package com.java.training.boot.endpoint;

import com.java.training.boot.jaxb.schema.rq.EmployeeDeleteRQ;
import com.java.training.boot.jaxb.schema.common.Employee;
import com.java.training.boot.jaxb.schema.rs.EmployeeDeleteRS;
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
public class EmployeeDeleteEndpoint {
    @Autowired
    private EmployeeService employeeService;

    @PayloadRoot(namespace = "http://www.iTech.com/employee/portal", localPart = "EmployeeDeleteRQ")
    @ResponsePayload
    public EmployeeDeleteRS deleteEmployee(final @RequestPayload EmployeeDeleteRQ employeeDeleteRQ) {
        EmployeeDeleteRS result = new EmployeeDeleteRS();
        Employee employee = employeeService.deleteEmployee(employeeDeleteRQ.getId().intValue());
        String message = "Employee " ;
        if (employee != null) {
            message += employee + " deleted successfully";
        } else {
            message += "specified by Id = [" + employeeDeleteRQ.getId().toString() + "] is not found";
        }
        result.setMessage(message);
        return result;
    }
}
