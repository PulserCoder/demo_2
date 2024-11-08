package com.example.demo.controllers;

import com.example.demo.services.DepartmentService;
import com.example.demo.models.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/salary/max")
    public Employee findEmployeeWithMaximalSalaryByDepartment(@PathVariable("id") int department) {
        return departmentService.findMaximalSalaryByDepartment(department);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Employee findEmployeeWithMinimalSalaryByDepartment(@PathVariable("id") int department) {
        return departmentService.findMinimalSalaryByDepartment(department);
    }

    @GetMapping(path = "{id}/employees")
    public Object findAllEmployeesByDepartment(@PathVariable("id") int department) {
        return departmentService.getAllEmployeesByDepartment(department);
    }

    @GetMapping("employees")
    public Object findAllEmployees() {
        return departmentService.getAllEmployees();
    }
}
