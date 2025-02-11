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
import java.time.LocalDate;

class Employee extends User {
    public Employee(String employee_id, String last_name, String first_name, LocalDate birth_day, String address, 
                    String phone_number, String sss, String philhealth, String tin, String pagibig, 
                    String status, String position, String department, String supervisor, int basic_salary, 
                    int rice_subsidy, int phone_allowance, int clothing_allowance, int gross_semi_monthly_rate, 
                    int hourly_rate, String username, String password) {
        super(employee_id, last_name, first_name, birth_day, address, phone_number, sss, philhealth, 
              tin, pagibig, status, position, department, supervisor, basic_salary, rice_subsidy, 
              phone_allowance, clothing_allowance, gross_semi_monthly_rate, hourly_rate, username, password);
    }

    @Override
    public void viewEmployeeDetails() {
    System.out.println("Employee Details:");
    System.out.println("ID: " + employee_id);
    System.out.println("Name: " + first_name + " " + last_name);
    System.out.println("Department: " + department);
    System.out.println("Position: " + position);
    System.out.println("Basic Salary: " + basic_salary);
    System.out.println("---------------------------");
}

    // Getters
    public String getEmployeeId() { return employee_id; }
    public String getLastName() { return last_name; }
    public String getFirstName() { return first_name; }
    public LocalDate getBirthDay() { return birth_day; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phone_number; }
    public String getSSS() { return sss; }
    public String getPhilHealth() { return philhealth; }
    public String getTIN() { return tin; }
    public String getPagibig() { return pagibig; }
    public String getStatus() { return status; }
    public String getPosition() { return position; }
    public String getDepartment() { return department; }
    public String getSupervisor() { return supervisor; }
    public int getBasicSalary() { return basic_salary; }
    public int getRiceSubsidy() { return rice_subsidy; }
    public int getPhoneAllowance() { return phone_allowance; }
    public int getClothingAllowance() { return clothing_allowance; }
    public int getGrossSemiMonthlyRate() { return gross_semi_monthly_rate; }
    public int getHourlyRate() { return hourly_rate; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // Setters
    public void setEmployeeId(String employee_id) { this.employee_id = employee_id; }
    public void setLastName(String last_name) { this.last_name = last_name; }
    public void setFirstName(String first_name) { this.first_name = first_name; }
    public void setBirthDay(LocalDate birth_day) { this.birth_day = birth_day; }
    public void setAddress(String address) { this.address = address; }
    public void setPhoneNumber(String phone_number) { this.phone_number = phone_number; }
    public void setSSS(String sss) { this.sss = sss; }
    public void setPhilHealth(String philhealth) { this.philhealth = philhealth; }
    public void setTIN(String tin) { this.tin = tin; }
    public void setPagibig(String pagibig) { this.pagibig = pagibig; }
    public void setStatus(String status) { this.status = status; }
    public void setPosition(String position) { this.position = position; }
    public void setDepartment(String department) { this.department = department; }
    public void setSupervisor(String supervisor) { this.supervisor = supervisor; }
    public void setBasicSalary(int basic_salary) { this.basic_salary = basic_salary; }
    public void setRiceSubsidy(int rice_subsidy) { this.rice_subsidy = rice_subsidy; }
    public void setPhoneAllowance(int phone_allowance) { this.phone_allowance = phone_allowance; }
    public void setClothingAllowance(int clothing_allowance) { this.clothing_allowance = clothing_allowance; }
    public void setGrossSemiMonthlyRate(int gross_semi_monthly_rate) { this.gross_semi_monthly_rate = gross_semi_monthly_rate; }
    public void setHourlyRate(int hourly_rate) { this.hourly_rate = hourly_rate; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}

