/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph_payrollsystem;

/**
 *
 * @author chris
 */
import java.time.LocalDate;
public class HR  extends User{
    public HR(String employee_id, String last_name, String first_name, LocalDate birth_day, String address, 
                    String phone_number, String sss, String philhealth, String tin, String pagibig, 
                    String status, String position, String department, String supervisor, int basic_salary, 
                    int rice_subsidy, int phone_allowance, int clothing_allowance, int gross_semi_monthly_rate, 
                    int hourly_rate, String username, String password) {
        super(employee_id, last_name, first_name, birth_day, address, phone_number, sss, philhealth, 
              tin, pagibig, status, position, department, supervisor, basic_salary, rice_subsidy, 
              phone_allowance, clothing_allowance, gross_semi_monthly_rate, hourly_rate, username, password);
    }

    public void manageEmployee(Employee employee) {
        System.out.println("Managing employee: " + employee.getFirstName() + " " + employee.getLastName());
    }

    @Override
    public void viewEmployeeDetails() {
    System.out.println("---------------------------");
    System.out.println("Employee Details:");
    System.out.println("ID: " + employee_id);
    System.out.println("Name: " + first_name + " " + last_name);
    System.out.println("Department: " + department);
    System.out.println("Position: " + position);
    System.out.println("Basic Salary: " + basic_salary);
    System.out.println("---------------------------");
}
}
