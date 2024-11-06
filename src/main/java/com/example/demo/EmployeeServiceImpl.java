package com.example.demo;

import com.example.demo.exceptions.EmployeeAlreadyAddedException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.exceptions.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LogManager.getLogger(EmployeeServiceImpl.class);
    private final List<Employee> employees;
    private final int maxEmployees = 10;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<Employee>();
    }

    @Override
    public int countAllSalaries() {
        return employees.stream()
                .mapToInt(e -> e.getSalary()).sum();
    }

    @Override
    public Employee findMinimalSalary() {
        return employees.stream().min(Comparator.comparingInt(e -> e.getSalary())).orElse(null);
    }

    @Override
    public Employee findMaximalSalary() {
        return employees.stream().max(Comparator.comparingInt(e -> e.getSalary())).orElse(null);
    }

    @Override
    public int getAverageSalary() {
        return countAllSalaries() / employees.size();
    }


    @Override
    public Employee findMinimalSalaryByDepartment(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElse(null);
    }

    @Override
    public Employee findMaximalSalaryByDepartment(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElse(null);
    }

    @Override
    public int getQuantityEmployeesByDepartment(int department) {
        return (int) employees.stream()
                .filter(e -> e.getDepartment() == department)
                .count();
    }

    @Override
    public int countAllSalariesByDepartment(int department) {
        return employees.stream().mapToInt(e -> e.getSalary()).sum();
    }

    @Override
    public int getAverageSalaryByDepartment(int department) {
        return countAllSalariesByDepartment(department) / getQuantityEmployeesByDepartment(department);
    }


    @Override
    public Employee addEmployee(String firstName, String lastName,
                                int salary, int department) {
        if (employees.size() >= maxEmployees) {
            log.warn("какое-то говно");
            throw new EmployeeStorageIsFullException("Превышен лимит по сотрудникам");
        }
        try {
            getEmployeeByFullName(firstName + " " + lastName);
            log.warn("какое-то говно");
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже добавлен");
        } catch (EmployeeNotFoundException e) {
            Employee newEmployee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, department);
            employees.add(newEmployee);
            return newEmployee;
        }

    }

    @Override
    public Optional<Employee> deleteEmployee(String fullName) {
        Optional<Employee> employeeToDelete = employees.stream()
                .filter(employee -> fullName.equals(employee.getFullName()))
                .findFirst();

        employeeToDelete.ifPresent(employees::remove);
        return employeeToDelete;
    }

    @Override
    public Employee getEmployeeByFullName(String fullName) {
        return employees.stream().filter(e -> e.getFullName().equals(fullName)).findFirst().orElseThrow(()
                -> new EmployeeNotFoundException("Такого пользователя не существует"));
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(int department) {
        return employees.stream().filter(e -> e.getDepartment() == department).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}

