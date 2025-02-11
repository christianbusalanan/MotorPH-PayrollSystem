/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph_payrollsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author chris
 */
class PayrollManager extends HR{
    private final List<Employee> employeeList;
    
    public PayrollManager(String employee_id, String last_name, String first_name, LocalDate birth_day, String address, 
                    String phone_number, String sss, String philhealth, String tin, String pagibig, 
                    String status, String supervisor, int basic_salary, 
                    int rice_subsidy, int phone_allowance, int clothing_allowance, int gross_semi_monthly_rate, 
                    int hourly_rate, String username, String password) {
        super(employee_id, last_name, first_name, birth_day, address, phone_number, sss, philhealth, 
              tin, pagibig, status, "Payroll Manager", "Human Resources", supervisor, basic_salary, rice_subsidy, 
              phone_allowance, clothing_allowance, gross_semi_monthly_rate, hourly_rate, username, password);
         this.employeeList = new ArrayList<>();
    }
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        System.out.println("Employee " + employee.getFirstName() + " " + employee.getLastName() + " added successfully.");
        }
    
    // Read: List all employees
    public void listEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employee List:");
            for (Employee emp : employeeList) {
                emp.viewEmployeeDetails();
            }
        }
    }

    // Update: Update an employee by ID
    public void updateEmployee(String id, String newName, String newPosition, int newSalary) {
        Optional<Employee> employee = employeeList.stream()
            .filter(emp -> emp.getEmployeeId().equals(id))
            .findFirst();

        if (employee.isPresent()) {
            Employee emp = employee.get();
            emp.setFirstName(newName);
            emp.setPosition(newPosition);
            emp.setBasicSalary(newSalary);
            System.out.println("Employee " + id + " updated successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    // Delete: Remove an employee by ID
    public void deleteEmployee(String id) {
        if (employeeList.removeIf(emp -> emp.getEmployeeId().equals(id))) {
            System.out.println("Employee with ID " + id + " removed successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
}
    

