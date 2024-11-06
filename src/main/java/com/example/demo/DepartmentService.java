package com.example.demo;


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
        return employeeService.getAllEmployeesByDepartment(department);
    }

    public int getAverageSalaryByDepartment(int department) {
        return employeeService.getAverageSalaryByDepartment(department);
    }

    public int countAllSalariesByDepartment(int department) {
        return employeeService.countAllSalariesByDepartment(department);
    }

    public int getQuantityEmployeesByDepartment(int department) {
        return employeeService.getQuantityEmployeesByDepartment(department);
    }

    public Employee findMaximalSalaryByDepartment(int department) {
        return employeeService.findMaximalSalaryByDepartment(department);
    }

    public Employee findMinimalSalaryByDepartment(int department) {
        return employeeService.findMinimalSalaryByDepartment(department);
    }

    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


}
