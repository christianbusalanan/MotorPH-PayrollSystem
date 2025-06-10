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
- [External Testing](#external-testing)
- [Summary](#summary)
- [Repository](#github-repository)

---

## Project Description
The **MotorPH Payroll System** is a desktop-based Java application designed to streamline essential HR tasks. It allows HR personnel to manage employee records, handle leave requests, and process payroll with accuracy and efficiency. The system supports secure login, detailed employee management, and automated payroll calculations.

---

## Group Members
- Busalanan, C.N.
- Guevara, K.
- Ricohermozo, J.
- Nacpil, A.J.
- Torrijos, E.M.
- Dela Cruz, P.P.

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

## External Testing

| **Scenario**            | **Test Case ID** | **Test Case**                           | **Steps**                                      | **Expected Result**                             | **Actual Result**                                | **Result** |
|-------------------------|------------------|-----------------------------------------|-----------------------------------------------|-----------------------------------------------|-----------------------------------------------|------------|
| Application Launch      | 1                | Application Launched                   | Run application on IDE and compile properly  | Login page comes up                          | Login page comes up                          | ✅ Pass    |
|                         | 2                | Login with wrong/empty credentials      | Enter wrong credentials/leave fields empty   | Warning window pops up                       | Warning window pops up                       | ✅ Pass    |
|                         | 3                | Login with proper credentials           | Enter HR/Finance/Employee credentials        | Logs in accordingly                          | HR & Finance logged in, Employee failed      | ✅ Pass    |
| Employee Functions      | 4                | Employee login attempt                  | Enter employee login credentials             | Employee portal opens                        | Warning popup appears despite correct login | ❌ Fail    |
|                         | 5                | Employee portal functions               | Try to use functions                         | Functions should work                        | Not functional                              | ❌ Fail    |
|                         | 6                | Leave request submission                | Submit leave request                         | Request submitted successfully               | Not functional                              | ❌ Fail    |
| Finance Functions       | 7                | Finance login                           | Enter finance credentials                    | Finance portal opens                         | Finance portal opens                         | ✅ Pass    |
|                         | 8                | Calculate Payslip                       | Click 'Calculate Payslip'                    | Employee salary calculated                   | No response                                  | ❌ Fail    |
|                         | 9                | Process Payroll                         | Click 'Process Payroll'                      | Payroll processed successfully               | No response                                  | ❌ Fail    |
| HR Functions            | 10               | HR login                                | Enter HR credentials                         | HR portal opens                              | HR portal opens                              | ✅ Pass    |
|                         | 11               | Manage Employees                        | Create, update, and delete employees         | All functions should work                    | Update failed, others work                   | ✅ Pass    |
|                         | 12               | Leave management                        | Approve/reject leave requests                | Status updates successfully                  | Status updates successfully                  | ✅ Pass    |

---

## Summary
All internal test cases passed, but external testing revealed some issues with employee functionality and payroll processing. The **MotorPH Payroll System** meets most requirements but requires fixes in certain areas before deployment.

---

## GitHub Repository
[🔗 MotorPH Payroll System Repo](https://github.com/christianbusalanan/MotorPH-PayrollSystem)  
[🌍 GitHub Pages Deployment](https://santiagoian01.github.io/MotorPH-PayrollSystem/)
