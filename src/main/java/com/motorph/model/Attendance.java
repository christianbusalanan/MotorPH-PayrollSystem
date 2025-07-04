package com.motorph.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    private String employeeId;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;

    // Default constructor
    public Attendance() {}

    // Constructor with all fields
    public Attendance(String employeeId, LocalDate date, LocalTime timeIn, LocalTime timeOut) {
        this.employeeId = employeeId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTimeIn() { return timeIn; }
    public void setTimeIn(LocalTime timeIn) { this.timeIn = timeIn; }

    public LocalTime getTimeOut() { return timeOut; }
    public void setTimeOut(LocalTime timeOut) { this.timeOut = timeOut; }

    // Calculate hours worked for this day
    public double getHoursWorked() {
        if (timeIn != null && timeOut != null) {
            return (double) java.time.Duration.between(timeIn, timeOut).toMinutes() / 60.0;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "employeeId='" + employeeId + '\'' +
                ", date=" + date +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                '}';
    }
}