package Test;

import com.motorph.model.Employee;
import com.motorph.model.Payroll;
import com.motorph.service.PayrollCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class PayrollIntegrationTest {
    
    private PayrollCalculationService calculationService;
    private Employee sampleEmployee;
    
    @BeforeEach
    void setUp() {
        calculationService = new PayrollCalculationService();
        
        sampleEmployee = new Employee();
        sampleEmployee.setEmployeeId("EMP001");
        sampleEmployee.setFirstName("John");
        sampleEmployee.setLastName("Doe");
        sampleEmployee.setBasicSalary(50000.0);
        sampleEmployee.setHourlyRate(312.5);
        sampleEmployee.setPosition("Developer");
    }
    
    @Test
    void testFullPayrollCalculation() {
        // Simulate 160 hours worked
        double hoursWorked = 160.0;
        
        // Calculate gross income
        double grossIncome = calculationService.calculateGrossIncome(
            sampleEmployee.getHourlyRate(), hoursWorked
        );
        assertEquals(50000.0, grossIncome, 0.01);
        
        // Calculate deductions
        double sss = calculationService.calculateSSSContribution(grossIncome);
        double philhealth = calculationService.calculatePhilhealthContribution(grossIncome);
        double pagibig = calculationService.calculatePagibigContribution(grossIncome);
        
        // Calculate taxable income and tax
        double taxableIncome = grossIncome - (sss + philhealth + pagibig);
        double tax = calculationService.calculateWithholdingTax(taxableIncome);
        
        // Verify calculations are reasonable
        assertTrue(sss > 0);
        assertTrue(philhealth > 0);
        assertTrue(pagibig > 0);
        assertTrue(tax >= 0); // Tax could be 0 for lower incomes
        
        // Calculate net salary (without benefits for simplicity)
        double totalDeductions = sss + philhealth + pagibig + tax;
        double netSalary = calculationService.calculateNetSalary(grossIncome, totalDeductions, 0);
        
        assertTrue(netSalary > 0);
        assertTrue(netSalary < grossIncome); // Net should be less than gross due to deductions
    }
    
    @Test
    void testPayrollObjectIntegration() {
        LocalDate start = LocalDate.of(2023, 1, 1);
        LocalDate end = LocalDate.of(2023, 1, 15);
        
        Payroll payroll = new Payroll();
        payroll.setPayrollId("PAY001");
        payroll.setEmployeeId(sampleEmployee.getEmployeeId());
        payroll.setPeriodStart(start);
        payroll.setPeriodEnd(end);
        payroll.setWorkingHours(80.0); // Half month
        
        // Set some sample deductions and benefits
        payroll.setSssContribution(500.0);
        payroll.setPhilhealthContribution(300.0);
        payroll.setPagibigContribution(100.0);
        payroll.setWithholdingTax(2000.0);
        payroll.setRiceSubsidy(1500.0);
        payroll.setPhoneAllowance(800.0);
        payroll.setClothingAllowance(1000.0);
        
        // Test calculated methods
        double grossIncome = payroll.getGrossIncome(sampleEmployee.getHourlyRate());
        double totalDeductions = payroll.getTotalDeductions();
        double totalBenefits = payroll.getTotalBenefits();
        double netSalary = payroll.getNetSalary(sampleEmployee.getHourlyRate());
        
        assertEquals(25000.0, grossIncome, 0.01); // 80 hours * 312.5
        assertEquals(2900.0, totalDeductions, 0.01);
        assertEquals(3300.0, totalBenefits, 0.01);
        assertEquals(25400.0, netSalary, 0.01); // 25000 + 3300 - 2900
    }
}