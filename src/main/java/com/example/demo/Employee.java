package com.example.demo;

public class Employee {
    private int salary;
    private int department;
    private String firstName;
    private String lastName;
    private String fullName;

    public Employee(String firstName, String lastName, int salary, int department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.salary = salary;
        this.department = department;

    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.fullName = firstName + " " + lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;

    }

    @Override
    public boolean equals(Object other) {
        if (getClass() != other.getClass()) {
            return false;
        }
        Employee employee = (Employee) other;
        return getFullName().equals(employee.getFullName());
    }

    @Override
    public int hashCode() {
        return this.fullName.hashCode();
    }

    @Override
    public String toString() {
        return "\nFull Name: " + getFullName();
    }


}
