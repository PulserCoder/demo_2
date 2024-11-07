package com.example.demo.services;


import com.example.demo.models.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getAllEmployeesByDepartment(int department) {
        return employeeService.getEmployees().stream().filter(e -> e.getDepartment() == department).collect(Collectors.toList());
    }

    public int getAverageSalaryByDepartment(int department) {
        return countAllSalariesByDepartment(department) / getQuantityEmployeesByDepartment(department);
    }

    public int countAllSalariesByDepartment(int department) {
        return employeeService.getEmployees().stream().mapToInt(e -> e.getSalary()).sum();
    }

    public int getQuantityEmployeesByDepartment(int department) {
        return (int) employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .count();
    }

    public Employee findMaximalSalaryByDepartment(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElse(null);
    }

    public Employee findMinimalSalaryByDepartment(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElse(null);
    }

    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


}
