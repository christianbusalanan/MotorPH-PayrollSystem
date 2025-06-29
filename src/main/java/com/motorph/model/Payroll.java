package com.motorph.model;

import java.time.LocalDate;

public class Payroll {
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

    // Getters and Setters
    public String getPayrollId() { return payrollId; }
    public void setPayrollId(String payrollId) { this.payrollId = payrollId; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public LocalDate getPeriodStart() { return periodStart; }
    public void setPeriodStart(LocalDate periodStart) { this.periodStart = periodStart; }

    public LocalDate getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(LocalDate periodEnd) { this.periodEnd = periodEnd; }

    public double getWorkingHours() { return workingHours; }
    public void setWorkingHours(double workingHours) { this.workingHours = workingHours; }

    public double getSssContribution() { return sssContribution; }
    public void setSssContribution(double sssContribution) { this.sssContribution = sssContribution; }

    public double getPhilhealthContribution() { return philhealthContribution; }
    public void setPhilhealthContribution(double philhealthContribution) { this.philhealthContribution = philhealthContribution; }

    public double getPagibigContribution() { return pagibigContribution; }
    public void setPagibigContribution(double pagibigContribution) { this.pagibigContribution = pagibigContribution; }

    public double getWithholdingTax() { return withholdingTax; }
    public void setWithholdingTax(double withholdingTax) { this.withholdingTax = withholdingTax; }

    public double getRiceSubsidy() { return riceSubsidy; }
    public void setRiceSubsidy(double riceSubsidy) { this.riceSubsidy = riceSubsidy; }

    public double getPhoneAllowance() { return phoneAllowance; }
    public void setPhoneAllowance(double phoneAllowance) { this.phoneAllowance = phoneAllowance; }

    public double getClothingAllowance() { return clothingAllowance; }
    public void setClothingAllowance(double clothingAllowance) { this.clothingAllowance = clothingAllowance; }

    // Calculated fields
    public double getGrossIncome(double hourlyRate) {
        return workingHours * hourlyRate;
    }

    public double getTotalDeductions() {
        return sssContribution + philhealthContribution + pagibigContribution + withholdingTax;
    }

    public double getTotalBenefits() {
        return riceSubsidy + phoneAllowance + clothingAllowance;
    }

    public double getNetSalary(double hourlyRate) {
        return getGrossIncome(hourlyRate) + getTotalBenefits() - getTotalDeductions();
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "payrollId='" + payrollId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                '}';
    }
}