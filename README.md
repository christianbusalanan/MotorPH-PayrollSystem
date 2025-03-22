# MotorPH Payroll System  
![Status](https://img.shields.io/badge/status-Completed-brightgreen)  
**Group 12 | Client: MotorPH**

---

## Table of Contents
- [Project Description](#project-description)
- [Group Members](#group-members)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Test Scenarios](#test-scenarios)
- [Internal Test Cases](#internal-test-cases)
- [Summary](#summary)
- [Repository](#github-repository)

---

## Project Description
The **MotorPH Payroll System** is a desktop-based Java application designed to streamline essential HR tasks. It allows HR personnel to manage employee records, handle leave requests, and process payroll with accuracy and efficiency. The system supports secure login, detailed employee management, and automated payroll calculations.

---

## Group Members
- Christian Noel Busalanan	
- Claude Derick Gigante	
- Ian Federico Santiago	
- James Earl Teriales
- Joshua Ricohermozo
- Kimberly Guevara
- Ratherford Abaya	

---

## Features
- Secure Login Authentication
- Employee Leave Request Submission and Tracking
- HR Manager CRUD Operations (Create, Read, Update, Delete)
- Payroll Calculation with Overtime, Deductions, and Net Pay
- Payslip Generation

---

## Tech Stack
- **Language:** Java  
- **IDE:** Netbeans, VSCODE, ECLIPSE
- **Database:** MySQL / SQLite 
- **OS Compatibility:** Windows  
- **Dependencies:** Java Runtime Environment

---

## Test Scenarios
1. Login Functionality  
2. Employee Leave Requests  
3. HR Manager Duties (CRUD operations, Approve/Reject Leave)  
4. Payroll Processing  

---

## Internal Test Cases

| **Scenario**           | **Test Case ID** | **Test Case**                  | **Steps**                                  | **Expected Result**                        | **Actual Result**                          | **Result** |
|------------------------|------------------|--------------------------------|-------------------------------------------|-------------------------------------------|-------------------------------------------|------------|
| Login Functionality    | TC-01            | Launch System                  | Run HRManagerDB class                     | Redirected to Login tab                   | Redirected to Login tab                   | ✅ Pass    |
|                        | TC-02            | Valid Login                    | Enter valid credentials and click Login   | Redirected to dashboard                   | Redirected to dashboard                   | ✅ Pass    |
|                        | TC-03            | Invalid Login                  | Enter invalid credentials and click Login | Error: Incorrect username and password    | Error: Incorrect username and password    | ✅ Pass    |
| Leave Request          | TC-04            | Open Leave Request Page        | Login → Navigate to Leave Request page    | Page shows leave history & request form   | Page shows leave history & request form   | ✅ Pass    |
|                        | TC-05            | Submit Leave Request           | Input dates, select leave type, submit    | Leave request submitted successfully      | Leave request submitted successfully      | ✅ Pass    |
|                        | TC-06            | View Submitted Leave Request   | Submit request → Check history section    | Latest request appears at the top         | Latest request appears at the top         | ✅ Pass    |
| HR Manager Duties      | TC-07            | View Employee Details          | Click “Employee Details” → Fill fields    | Employee details are displayed            | Employee details are displayed            | ✅ Pass    |
|                        | TC-08            | CRUD Employee Records          | Input details → Add/Update/Delete         | Record updated accordingly                | Record updated accordingly                | ✅ Pass    |
|                        | TC-09            | Approve/Reject Leave Requests  | Enter employee number → Set status        | Leave request status updated              | Leave request status updated              | ✅ Pass    |
| Payroll Process        | TC-10            | Access Payroll Dashboard       | Login → Click “Process Payroll”           | Routed to payroll processing page         | Routed to payroll processing page         | ✅ Pass    |
|                        | TC-11            | Process Payroll                | Input data → Calculate → Process Payroll  | Payroll processed successfully            | Payroll processed successfully            | ✅ Pass    |
|                        | TC-12            | View Payslip                   | Process payroll → Confirm → View payslip  | Payslip displayed accurately              | Payslip displayed accurately              | ✅ Pass    |

---

## Summary
All test cases passed during internal testing. The **MotorPH Payroll System** meets the specified requirements for login, leave management, employee record handling, and payroll processing. The system is ready for deployment and fulfills its role in supporting efficient and accurate HR operations.

---

## GitHub Repository
[🔗 MotorPH Payroll System Repo](https://github.com/christianbusalanan/MotorPH-PayrollSystem)
