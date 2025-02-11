/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph_payrollsystem;

import java.time.LocalDate;

/**
 *
 * @author chris
 */
public class PayrollStaff extends HR {
    public PayrollStaff(String employee_id, String last_name, String first_name, LocalDate birth_day, 
                    String address, String phone_number, String sss, String philhealth, String tin, 
                    String pagibig, String status, String supervisor, int basic_salary, int rice_subsidy, 
                    int phone_allowance, int clothing_allowance, int gross_semi_monthly_rate, 
                    int hourly_rate, String username, String password) {
    
    super(employee_id, last_name, first_name, birth_day, address, phone_number, sss, philhealth, tin, 
          pagibig, status, "Payroll Staff", "Human Resources", supervisor, basic_salary, rice_subsidy, 
          phone_allowance, clothing_allowance, gross_semi_monthly_rate, hourly_rate, username, password);
}
        
        public void processPayroll(Employee employee) {
        System.out.println("Processing payroll for " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("Gross Salary: " + employee.getGrossSemiMonthlyRate());
        System.out.println("Basic Salary: " + employee.getBasicSalary());
    }
        
}
