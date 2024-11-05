package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("salary") int salary,
                                @RequestParam("departmentId") int department) {
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "remove")
    public Optional<Employee> removeEmployee(@RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName) {
        return employeeService.deleteEmployee(firstName + " " + lastName);
    }

    @GetMapping(path = "find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        return employeeService.getEmployeeByFullName(firstName + " " + lastName);
    }

    @GetMapping(path = "/departments/max-salary")
    public Employee findEmployeeWithMaximalSalaryByDepartment(@RequestParam("departmentId") int department) {
        return employeeService.findMaximalSalaryByDepartment(department);
    }

    @GetMapping(path = "/departments/min-salary")
    public Employee findEmployeeWithMinimalSalaryByDepartment(@RequestParam("departmentId") int department) {
        return employeeService.findMinimalSalaryByDepartment(department);
    }

    @GetMapping(path = "/departments/all")
    public Object findAllEmployeesByDepartment(@RequestParam(value = "departmentId", required = false) Integer department) {
        if (department != null) {
            return employeeService.getAllEmployeesByDepartment(department);
        } else {
            return employeeService.getAllEmployees().stream()
                    .collect(Collectors.groupingBy(e -> e.getDepartment()));
        }
    }

}
