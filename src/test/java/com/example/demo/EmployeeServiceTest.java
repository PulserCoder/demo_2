package com.example.demo;

import com.example.demo.exceptions.EmployeeAlreadyAddedException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.exceptions.EmployeeStorageIsFullException;
import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {
        employeeService.addEmployee("John", "Doe", 50000, 1);
        employeeService.addEmployee("Jane", "Smith", 60000, 2);
        employeeService.addEmployee("Bob", "Brown", 45000, 1);
    }

    @Test
    public void countAllSalaries() {
        int totalSalaries = employeeService.countAllSalaries();
        assertEquals(155000, totalSalaries);
    }

    @Test
    public void findMinimalSalary() {
        Employee employee = employeeService.findMinimalSalary();
        assertNotNull(employee);
        assertEquals(45000, employee.getSalary());
    }

    @Test
    public void findMaximalSalary() {
        Employee employee = employeeService.findMaximalSalary();
        assertNotNull(employee);
        assertEquals(60000, employee.getSalary());
    }

    @Test
    public void getAverageSalary() {
        int averageSalary = employeeService.getAverageSalary();
        assertEquals(51666, averageSalary);
    }

    @Test
    public void addEmployee() {
        Employee newEmployee = employeeService.addEmployee("Alice", "Johnson", 55000, 3);
        assertNotNull(newEmployee);
        assertEquals("Alice Johnson", newEmployee.getFullName());
    }

    @Test
    public void addEmployeeThrowsExceptionWhenFull() {
        for (int i = 0; i < 7; i++) {
            employeeService.addEmployee("Test", "User" + i, 40000, 1);
        }
        assertThrows(EmployeeStorageIsFullException.class, () ->
                employeeService.addEmployee("Extra", "User", 40000, 1));
    }

    @Test
    public void deleteEmployee() {
        Optional<Employee> deleted = employeeService.deleteEmployee("John Doe");
        assertTrue(deleted.isPresent());
        assertEquals("John Doe", deleted.get().getFullName());
        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.getEmployeeByFullName("John Doe"));
    }

    @Test
    public void deleteEmployeeNotFound() {
        Optional<Employee> deleted = employeeService.deleteEmployee("Nonexistent Employee");
        assertFalse(deleted.isPresent());
    }

    @Test
    public void getEmployeeByFullName() {
        Employee employee = employeeService.getEmployeeByFullName("Jane Smith");
        assertNotNull(employee);
        assertEquals(60000, employee.getSalary());
    }

    @Test
    public void getEmployeeByFullNameNotFound() {
        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.getEmployeeByFullName("Nonexistent Employee"));
    }

    @Test
    public void getAllEmployees() {
        Map<Integer, List<Employee>> employeesByDepartment = employeeService.getAllEmployees();
        assertEquals(2, employeesByDepartment.size());
        assertEquals(2, employeesByDepartment.get(1).size());
        assertEquals(1, employeesByDepartment.get(2).size());
    }

    @Test
    public void getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        assertEquals(3, employees.size());
    }
}
