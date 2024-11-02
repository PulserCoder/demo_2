package com.example.demo;

public class Employee {
    private String firstName;
    private String lastName;
    private String fullName;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
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
        return  "\nFull Name: " + getFullName();
    }


}
