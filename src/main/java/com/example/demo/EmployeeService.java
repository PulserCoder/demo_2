package com.example.demo;

import com.example.demo.exceptions.EmployeeAlreadyAddedException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.exceptions.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final List<Employee> employees;
    private final int maxEmployees = 10;

    public EmployeeService() {
        this.employees = new ArrayList<Employee>();
    }

    public int countAllSalaries() {
        return employees.stream()
                .mapToInt(e -> e.getSalary()).sum();
    }

    public Employee findMinimalSalary() {
        return employees.stream().min(Comparator.comparingInt(e -> e.getSalary())).orElse(null);
    }

    public Employee findMaximalSalary() {
        return employees.stream().max(Comparator.comparingInt(e -> e.getSalary())).orElse(null);
    }

    public int getAverageSalary() {
        return countAllSalaries() / employees.size();
    }


    public Employee findMinimalSalaryByDepartment(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElse(null);
    }

    public Employee findMaximalSalaryByDepartment(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElse(null);
    }

    public int getQuantityEmployeesByDepartment(int department) {
        return (int) employees.stream()
                .filter(e -> e.getDepartment() == department)
                .count();
    }

    public int countAllSalariesByDepartment(int department) {
        return employees.stream().mapToInt(e -> e.getSalary()).sum();
    }

    public int getAverageSalaryByDepartment(int department) {
        return countAllSalariesByDepartment(department) / getQuantityEmployeesByDepartment(department);
    }


    public Employee addEmployee(String firstName, String lastName,
                                int salary, int department) {
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException("Превышен лимит по сотрудникам");
        }
        try {
            getEmployeeByFullName(firstName + " " + lastName);
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже добавлен");
        } catch (EmployeeNotFoundException e) {
            Employee newEmployee = new Employee(firstName, lastName, salary, department);
            employees.add(newEmployee);
            return newEmployee;
        }

    }

    public Optional<Employee> deleteEmployee(String fullName) {
        Optional<Employee> employeeToDelete = employees.stream()
                .filter(employee -> fullName.equals(employee.getFullName()))
                .findFirst();

        employeeToDelete.ifPresent(employees::remove);
        return employeeToDelete;
    }

    public Employee getEmployeeByFullName(String fullName) {
        return employees.stream().filter(e -> e.getFullName().equals(fullName)).findFirst().orElseThrow(()
                -> new EmployeeNotFoundException("Такого пользователя не существует"));
    }

    public List<Employee> getAllEmployeesByDepartment(int department) {
        return employees.stream().filter(e -> e.getDepartment() == department).collect(Collectors.toList());
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    static void validateName(String name) {
        if (StringUtils.isBlank(name) || !StringUtils.isAlpha(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name: " + name);
        }
    }
}

