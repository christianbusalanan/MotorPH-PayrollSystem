package com.motorph.model;

import com.motorph.interfaces.Calculable;
import java.time.LocalDate;

/**
 * Payroll class implementing Calculable interface
 * Demonstrates Composition, Encapsulation, and Interface Implementation
 */
public class Payroll implements Calculable {
    private String payrollId;
    private String employeeId;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private double workingHours;
    private double sssContribution;
    private double philhealthContribution;
    private double pagibigContribution;
    private double withholdingTax;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    
    // Composition - Payroll HAS-A Employee reference
    private Employee employee;

    // Default constructor
    public Payroll() {}

    // Constructor with all fields
    public Payroll(String payrollId, String employeeId, LocalDate periodStart, LocalDate periodEnd,
                   double workingHours, double sssContribution, double philhealthContribution,
                   double pagibigContribution, double withholdingTax, double riceSubsidy,
                   double phoneAllowance, double clothingAllowance) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.workingHours = workingHours;
        this.sssContribution = sssContribution;
        this.philhealthContribution = philhealthContribution;
        this.pagibigContribution = pagibigContribution;
        this.withholdingTax = withholdingTax;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
    }
    
    // Implementation of Calculable interface
    @Override
    public double calculate() {
        return getNetSalary();
    }
    
    @Override
    public boolean isValid() {
        return payrollId != null && !payrollId.isEmpty() &&
               employeeId != null && !employeeId.isEmpty() &&
               periodStart != null && periodEnd != null &&
               periodStart.isBefore(periodEnd) &&
               workingHours >= 0;
    }

    // Polymorphism - Method Overloading for different calculation types
    public double getGrossIncome() {
        if (employee != null) {
            return workingHours * employee.getHourlyRate();
        }
        return 0.0;
    }
    
    public double getGrossIncome(double hourlyRate) {
        return workingHours * hourlyRate;
    }
    
    public double getGrossIncome(double hourlyRate, double overtimeRate, double overtimeHours) {
        double regularPay = (workingHours - overtimeHours) * hourlyRate;
        double overtimePay = overtimeHours * overtimeRate;
        return regularPay + overtimePay;
    }

    public double getTotalDeductions() {
        return sssContribution + philhealthContribution + pagibigContribution + withholdingTax;
    }

    public double getTotalBenefits() {
        return riceSubsidy + phoneAllowance + clothingAllowance;
    }

    public double getNetSalary() {
        return getGrossIncome() + getTotalBenefits() - getTotalDeductions();
    }
    
    public double getNetSalary(double hourlyRate) {
        return getGrossIncome(hourlyRate) + getTotalBenefits() - getTotalDeductions();
    }
    
    // Business logic methods
    public boolean isPeriodValid() {
        return periodStart != null && periodEnd != null && 
               periodStart.isBefore(periodEnd) &&
               periodStart.plusDays(31).isAfter(periodEnd); // Max 31 days
    }
    
    public int getPayPeriodDays() {
        if (isPeriodValid()) {
            return (int) java.time.temporal.ChronoUnit.DAYS.between(periodStart, periodEnd);
        }
        return 0;
    }

    // Encapsulation - Getters and Setters with validation
    public String getPayrollId() { return payrollId; }
    public void setPayrollId(String payrollId) { 
        if (payrollId != null && !payrollId.trim().isEmpty()) {
            this.payrollId = payrollId.trim();
        }
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { 
        if (employeeId != null && !employeeId.trim().isEmpty()) {
            this.employeeId = employeeId.trim();
        }
    }

    public LocalDate getPeriodStart() { return periodStart; }
    public void setPeriodStart(LocalDate periodStart) { this.periodStart = periodStart; }

    public LocalDate getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(LocalDate periodEnd) { this.periodEnd = periodEnd; }

    public double getWorkingHours() { return workingHours; }
    public void setWorkingHours(double workingHours) { 
        if (workingHours >= 0) {
            this.workingHours = workingHours;
        }
    }

    public double getSssContribution() { return sssContribution; }
    public void setSssContribution(double sssContribution) { 
        if (sssContribution >= 0) {
            this.sssContribution = sssContribution;
        }
    }

    public double getPhilhealthContribution() { return philhealthContribution; }
    public void setPhilhealthContribution(double philhealthContribution) { 
        if (philhealthContribution >= 0) {
            this.philhealthContribution = philhealthContribution;
        }
    }

    public double getPagibigContribution() { return pagibigContribution; }
    public void setPagibigContribution(double pagibigContribution) { 
        if (pagibigContribution >= 0) {
            this.pagibigContribution = pagibigContribution;
        }
    }

    public double getWithholdingTax() { return withholdingTax; }
    public void setWithholdingTax(double withholdingTax) { 
        if (withholdingTax >= 0) {
            this.withholdingTax = withholdingTax;
        }
    }

    public double getRiceSubsidy() { return riceSubsidy; }
    public void setRiceSubsidy(double riceSubsidy) { 
        if (riceSubsidy >= 0) {
            this.riceSubsidy = riceSubsidy;
        }
    }

    public double getPhoneAllowance() { return phoneAllowance; }
    public void setPhoneAllowance(double phoneAllowance) { 
        if (phoneAllowance >= 0) {
            this.phoneAllowance = phoneAllowance;
        }
    }

    public double getClothingAllowance() { return clothingAllowance; }
    public void setClothingAllowance(double clothingAllowance) { 
        if (clothingAllowance >= 0) {
            this.clothingAllowance = clothingAllowance;
        }
    }
    
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    @Override
    public String toString() {
        return "Payroll{" +
                "payrollId='" + payrollId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                ", netSalary=" + getNetSalary() +
                '}';
    }
}