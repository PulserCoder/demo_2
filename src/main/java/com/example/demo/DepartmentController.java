package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee findEmployeeWithMaximalSalaryByDepartment(@RequestParam("departmentId") int department) {
        return departmentService.findMaximalSalaryByDepartment(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee findEmployeeWithMinimalSalaryByDepartment(@RequestParam("departmentId") int department) {
        return departmentService.findMinimalSalaryByDepartment(department);
    }

    @GetMapping(path = "/all")
    public Object findAllEmployeesByDepartment(@RequestParam(value = "departmentId", required = false) Integer department) {
        if (department != null) {
            return departmentService.getAllEmployeesByDepartment(department);
        } else {
            return departmentService.getAllEmployees();
        }
    }
}
