package com.example.demo.services;

import com.example.demo.models.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {
    static void validateName(String name) {
        if (StringUtils.isBlank(name) || !StringUtils.isAlpha(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name: " + name);
        }
    }

    int countAllSalaries();

    Employee findMinimalSalary();

    Employee findMaximalSalary();

    int getAverageSalary();

    Employee getEmployeeByFullName(String fullName);

    Employee addEmployee(String firstName, String lastName,
                         int salary, int department);

    Optional<Employee> deleteEmployee(String fullName);

    Map<Integer, List<Employee>> getAllEmployees();

    List<Employee> getEmployees();
}
