package com.example.demo;

import com.example.demo.exceptions.EmployeeAlreadyAddedException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<Employee>();
    private final int maxEmployees = 10;

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException("Превышен лимит по сотрудникам");
        }
        try {
            getEmployeeByFullName(firstName + " " + lastName);
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже добавлен");
        } catch (EmployeeNotFoundException e) {
            Employee newEmployee = new Employee(firstName, lastName);
            employees.add(newEmployee);
            return newEmployee;
        }

    }

    public Employee deleteEmployee(String fullName) {
        for (int i = 0; i < employees.size(); i++) {
            if (fullName.equals(employees.get(i).getFullName())) {
                Employee employee = employees.get(i);
                employees.remove(i);
                return employee;
            }
        }
        throw new EmployeeNotFoundException("нет такого");
    }

    public Employee getEmployeeByFullName(String fullName) {
        for (Employee employee : employees) {
            if (employee.getFullName().equals(fullName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Такого пользователя не существует");
    }
}
