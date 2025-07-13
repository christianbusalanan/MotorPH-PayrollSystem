package com.motorph.interfaces;

import java.time.LocalDate;

/**
 * Interface for payable entities
 * Demonstrates Abstraction and Interface Segregation
 */
public interface Payable {
    double calculateGrossPay(LocalDate startDate, LocalDate endDate);
    double calculateNetPay(LocalDate startDate, LocalDate endDate);
    double getHourlyRate();
    void setHourlyRate(double rate);
}