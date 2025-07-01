package com.motorph.service;

public class PayrollCalculationService {
    
    public double calculateSSSContribution(double compensation) {
        if (compensation < 3250) return 135.00;
        else if (compensation < 3750) return 157.50;
        else if (compensation < 4250) return 180.00;
        else if (compensation < 4750) return 202.50;
        else if (compensation < 5250) return 225.00;
        else if (compensation < 5750) return 247.50;
        else if (compensation < 6250) return 270.00;
        else if (compensation < 6750) return 292.50;
        else if (compensation < 7250) return 315.00;
        else if (compensation < 7750) return 337.50;
        else if (compensation < 8250) return 360.00;
        else if (compensation < 8750) return 382.50;
        else if (compensation < 9250) return 405.00;
        else if (compensation < 9750) return 427.50;
        else if (compensation < 10250) return 450.00;
        else if (compensation < 10750) return 472.50;
        else if (compensation < 11250) return 495.00;
        else if (compensation < 11750) return 517.50;
        else if (compensation < 12250) return 540.00;
        else if (compensation < 12750) return 562.50;
        else if (compensation < 13250) return 585.00;
        else if (compensation < 13750) return 607.50;
        else if (compensation < 14250) return 630.00;
        else if (compensation < 14750) return 652.50;
        else if (compensation < 15250) return 675.00;
        else if (compensation < 15750) return 697.50;
        else if (compensation < 16250) return 720.00;
        else if (compensation < 16750) return 742.50;
        else if (compensation < 17250) return 765.00;
        else if (compensation < 17750) return 787.50;
        else if (compensation < 18250) return 810.00;
        else if (compensation < 18750) return 832.50;
        else if (compensation < 19250) return 855.00;
        else if (compensation < 19750) return 877.50;
        else if (compensation < 20250) return 900.00;
        else if (compensation < 20750) return 922.50;
        else if (compensation < 21250) return 945.00;
        else if (compensation < 21750) return 967.50;
        else if (compensation < 22250) return 990.00;
        else if (compensation < 22750) return 1012.50;
        else if (compensation < 23250) return 1035.00;
        else if (compensation < 23750) return 1057.50;
        else if (compensation < 24250) return 1080.00;
        else if (compensation < 24750) return 1102.50;
        else return 1125.00; // 24,750 and above
    }
    
    public double calculatePhilhealthContribution(double salary) {
        double philhealth =  salary * 0.015;
        return Math.round(philhealth * 100.0) / 100.0;
    }
    
    public double calculatePagibigContribution(double monthlySalary) {
    double contribution;
    if (monthlySalary >= 1000 && monthlySalary <= 1500) {
        contribution = 0.01 * monthlySalary;
    } else if (monthlySalary > 1500) {
        contribution = 0.02 * monthlySalary;
    } else {
        contribution = 0.00; // Below 1,000, no contribution
    }
    return Math.round(contribution * 100.0) / 100.0;
}

    public double calculateWithholdingTax(double monthlySalary) {
        double tax;
        if (monthlySalary <= 20832) {
            tax = 0.0;
        } else if (monthlySalary < 33333) {
            tax = (monthlySalary - 20833) * 0.20;
        } else if (monthlySalary < 66667) {
            tax = 2500 + (monthlySalary - 33333) * 0.25;
        } else if (monthlySalary < 166667) {
            tax = 10833 + (monthlySalary - 66667) * 0.30;
        } else if (monthlySalary < 666667) {
            tax = 40833.33 + (monthlySalary - 166667) * 0.32;
        } else {
            tax = 200833.33 + (monthlySalary - 666667) * 0.35;
        }
        return Math.round(tax * 100.0) / 100.0;
    }
    
    public double calculateGrossIncome(double hourlyRate, double hoursWorked) {
        double gross = hourlyRate * hoursWorked;
        return Math.round(gross * 100.0) / 100.0;
    }
    
    public double calculateNetSalary(double grossIncome, double totalDeductions, double totalBenefits) {
        double netSalary=  grossIncome + totalBenefits - totalDeductions;
        return Math.round(netSalary * 100.0) / 100.0;
    }
}