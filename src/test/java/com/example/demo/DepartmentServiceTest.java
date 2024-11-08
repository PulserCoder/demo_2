package com.example.demo;


import com.example.demo.models.Employee;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    private final List<Employee> mockEmployees = Arrays.asList(
            new Employee("John", "Doe", 50000, 1),
            new Employee("Jane", "Smith", 60000, 2),
            new Employee("Bob", "Brown", 45000, 1)
    );

    @BeforeEach
    public void setUp() {
        when(employeeService.getEmployees()).thenReturn(mockEmployees);
    }

    @Test
    public void getAllEmployeesByDepartment() {
        List<Employee> department1Employees = departmentService.getAllEmployeesByDepartment(1);
        assertEquals(2, department1Employees.size());
        assertTrue(department1Employees.stream().allMatch(e -> e.getDepartment() == 1));

        List<Employee> department2Employees = departmentService.getAllEmployeesByDepartment(2);
        assertEquals(1, department2Employees.size());
        assertEquals("Jane Smith", department2Employees.get(0).getFullName());

        List<Employee> department3Employees = departmentService.getAllEmployeesByDepartment(3);
        assertTrue(department3Employees.isEmpty());
    }

    @Test
    public void getAverageSalaryByDepartment() {
        int avgSalaryDept1 = departmentService.getAverageSalaryByDepartment(1);
        assertEquals(47500, avgSalaryDept1);

        int avgSalaryDept2 = departmentService.getAverageSalaryByDepartment(2);
        assertEquals(60000, avgSalaryDept2);
    }

    @Test
    public void countAllSalariesByDepartment() {
        int totalSalariesDept1 = departmentService.countAllSalariesByDepartment(1);
        assertEquals(95000, totalSalariesDept1);

        int totalSalariesDept2 = departmentService.countAllSalariesByDepartment(2);
        assertEquals(60000, totalSalariesDept2);
    }

    @Test
    public void getQuantityEmployeesByDepartment() {
        int qtyDept1 = departmentService.getQuantityEmployeesByDepartment(1);
        assertEquals(2, qtyDept1);

        int qtyDept2 = departmentService.getQuantityEmployeesByDepartment(2);
        assertEquals(1, qtyDept2);

        int qtyDept3 = departmentService.getQuantityEmployeesByDepartment(3);
        assertEquals(0, qtyDept3);
    }

    @Test
    public void findMaximalSalaryByDepartment() {
        Employee maxSalaryDept1 = departmentService.findMaximalSalaryByDepartment(1);
        assertNotNull(maxSalaryDept1);
        assertEquals("John Doe", maxSalaryDept1.getFullName());

        Employee maxSalaryDept2 = departmentService.findMaximalSalaryByDepartment(2);
        assertNotNull(maxSalaryDept2);
        assertEquals("Jane Smith", maxSalaryDept2.getFullName());

        Employee maxSalaryDept3 = departmentService.findMaximalSalaryByDepartment(3);
        assertNull(maxSalaryDept3);
    }

    @Test
    public void findMinimalSalaryByDepartment() {
        Employee minSalaryDept1 = departmentService.findMinimalSalaryByDepartment(1);
        assertNotNull(minSalaryDept1);
        assertEquals("Bob Brown", minSalaryDept1.getFullName());

        Employee minSalaryDept2 = departmentService.findMinimalSalaryByDepartment(2);
        assertNotNull(minSalaryDept2);
        assertEquals("Jane Smith", minSalaryDept2.getFullName());

        Employee minSalaryDept3 = departmentService.findMinimalSalaryByDepartment(3);
        assertNull(minSalaryDept3);
    }

}
