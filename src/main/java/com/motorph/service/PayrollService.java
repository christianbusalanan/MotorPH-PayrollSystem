package com.motorph.service;

import com.motorph.dao.AttendanceDAO;
import com.motorph.dao.BenefitsDAO;
import com.motorph.dao.PayrollDAO;
import com.motorph.model.Benefits;
import com.motorph.model.Employee;
import com.motorph.model.Payroll;
import java.time.LocalDate;
import java.util.List;

public class PayrollService {
    private final PayrollDAO payrollDAO;
    private final AttendanceDAO attendanceDAO;
    private final BenefitsDAO benefitsDAO;
    private final PayrollCalculationService calculationService;
    private final EmployeeService employeeService;
    
    public PayrollService() {
        this.payrollDAO = new PayrollDAO();
        this.attendanceDAO = new AttendanceDAO();
        this.benefitsDAO = new BenefitsDAO();
        this.calculationService = new PayrollCalculationService();
        this.employeeService = new EmployeeService();
    }
    
    public List<Payroll> getAllPayrolls() {
        return payrollDAO.getAllPayrolls();
    }
    
    public List<Payroll> getPayrollsByEmployeeId(String employeeId) {
        return payrollDAO.getPayrollsByEmployeeId(employeeId);
    }
    
    public Payroll calculatePayroll(String employeeId, LocalDate periodStart, LocalDate periodEnd) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            return null;
        }
        
        // Get working hours from attendance
        double workingHours = attendanceDAO.getTotalHoursWorked(employeeId, periodStart, periodEnd);
        
        // Calculate gross income
        double grossIncome = calculationService.calculateGrossIncome(employee.getHourlyRate(), workingHours);
        
        // Calculate deductions
        double sssContribution = calculationService.calculateSSSContribution(grossIncome);
        double philhealthContribution = calculationService.calculatePhilhealthContribution(grossIncome);
        double pagibigContribution = calculationService.calculatePagibigContribution(grossIncome);
        
        // Calculate taxable income (gross - mandatory contributions)
        double taxableIncome = grossIncome - (sssContribution + philhealthContribution + pagibigContribution);
        double withholdingTax = calculationService.calculateWithholdingTax(taxableIncome);
        
        // Get benefits
        Benefits benefits = benefitsDAO.getBenefitsByPosition(employee.getPosition());
        double riceSubsidy = benefits != null ? benefits.getRiceSubsidy() : 0.0;
        double phoneAllowance = benefits != null ? benefits.getPhoneAllowance() : 0.0;
        double clothingAllowance = benefits != null ? benefits.getClothingAllowance() : 0.0;
        
        // Create payroll record
        String payrollId = generatePayrollId(employeeId, periodStart, periodEnd);
        
        return new Payroll(
            payrollId, employeeId, periodStart, periodEnd, workingHours,
            sssContribution, philhealthContribution, pagibigContribution, withholdingTax,
            riceSubsidy, phoneAllowance, clothingAllowance
        );
    }
    
    public boolean processPayroll(Payroll payroll) {
        return payrollDAO.createPayroll(payroll);
    }
    
    private String generatePayrollId(String employeeId, LocalDate periodStart, LocalDate periodEnd) {
        return employeeId + "-" + periodStart + "-" + periodEnd;
    }
}