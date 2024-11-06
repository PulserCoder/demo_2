package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private static final Log log = LogFactory.getLog(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("salary") int salary,
                                @RequestParam("departmentId") int department) {
        EmployeeService.validateName(firstName);
        EmployeeService.validateName(lastName);
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "remove")
    public Optional<Employee> removeEmployee(@RequestParam("firstName") String firstName,
                                             @RequestParam("lastName") String lastName) {
        EmployeeService.validateName(firstName);
        EmployeeService.validateName(lastName);
        return employeeService.deleteEmployee(firstName + " " + lastName);
    }

    @GetMapping(path = "find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        EmployeeService.validateName(firstName);
        EmployeeService.validateName(lastName);
        return employeeService.getEmployeeByFullName(firstName + " " + lastName);
    }
}
