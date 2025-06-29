package com.motorph.model;

import java.time.LocalDate;

public class Employee {
    private String employeeId;
    private String lastName;
    private String firstName;
    private String username;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String status;
    private String position;
    private String department;
    private String supervisor;
    private double basicSalary;
    private double hourlyRate;

    // Default constructor
    public Employee() {}

    // Constructor with all fields
    public Employee(String employeeId, String lastName, String firstName, String username, 
                   LocalDate birthday, String address, String phoneNumber, String status,
                   String position, String department, String supervisor, 
                   double basicSalary, double hourlyRate) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.position = position;
        this.department = department;
        this.supervisor = supervisor;
        this.basicSalary = basicSalary;
        this.hourlyRate = hourlyRate;
    }

    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSupervisor() { return supervisor; }
    public void setSupervisor(String supervisor) { this.supervisor = supervisor; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + getFullName() + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}