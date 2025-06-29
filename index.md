# MotorPH Payroll System – Internal Testing

## Group 12  
**Client:** MotorPH  

---

## Project Description  
The MotorPH Payroll System is a desktop-based Java application that enables HR personnel to manage employee records, handle leave requests, and process payroll accurately. It streamlines essential HR tasks and ensures efficient employee management and payroll calculations.

---

## Test Scenarios  
1. Login Functionality  
2. Employee Leave Request  
3. HR Manager Duties (CRUD operations, Approve/Reject Leave)  
4. Payroll Process  

---

## Internal Test Cases

| **Scenario**               | **Test Case ID** | **Test Case**                                       | **Steps**                                                                                                                                                             | **Expected Result**                                                                                       | **Actual Result**                                                                                          | **Test Result** |
|---------------------------|------------------|----------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|-----------------|
| **Login Functionality**   | TC-01            | Run MotorPH Payroll System                         | 1. Compile and run the `HRManagerDB` class to launch the dashboard.                                                                                                  | User is redirected to the Login tab.                                                                      | User is redirected to the Login tab.                                                                       | Pass            |
|                           | TC-02            | Log in with valid credentials                      | 1. Enter valid username and password.<br>2. Click Login.                                                                                                             | User is redirected to the dashboard.                                                                      | User is redirected to the dashboard.                                                                       | Pass            |
|                           | TC-03            | Log in with invalid credentials                    | 1. Enter invalid username/password.<br>2. Click Login.                                                                                                               | User sees an error message: “Incorrect username and password.”                                            | User sees an error message: “Incorrect username and password.”                                             | Pass            |
| **Employee Leave Request**| TC-04            | Open Leave Request Page                            | 1. Log in.<br>2. Navigate to Leave Request page.                                                                                                                     | Page shows leave history and new request form.                                                             | Page shows leave history and new request form.                                                             | Pass            |
|                           | TC-05            | Submit Leave Request (All Types)                   | 1. Input Start Date.<br>2. Input End Date.<br>3. Select Leave Type.<br>4. Submit.                                                                                    | Leave request submitted successfully.                                                                     | Leave request submitted successfully.                                                                      | Pass            |
|                           | TC-06            | View Recently Submitted Leave Request              | 1. Submit leave request.<br>2. Observe history section.<br>3. Confirm via pop-up message.                                                                             | Most recent request appears at top of leave history.                                                      | Most recent request appears at top of leave history.                                                       | Pass            |
| **HR Manager Duties**     | TC-07            | View Employee Details                              | 1. Click “Employee Details” button.<br>2. Fill in required fields.                                                                                                   | Employee details are displayed.                                                                           | Employee details are displayed.                                                                            | Pass            |
|                           | TC-08            | Create/Update/Delete Employee Records              | 1. Input all employee details.<br>2. Click Add, Update, or Delete.<br>3. Confirm deletion prompt if needed.                                                          | Employee added/updated/deleted successfully.                                                              | Employee added/updated/deleted successfully.                                                               | Pass            |
|                           | TC-09            | Approve/Reject Leave Requests                      | 1. Enter employee number.<br>2. Set status to Pending, Approved, or Rejected.<br>3. Click “Update Leave Request.”                                                    | Leave request updated successfully.                                                                       | Leave request updated successfully.                                                                        | Pass            |
| **Payroll Process**       | TC-10            | Access Payroll Dashboard                           | 1. Log in with admin credentials.<br>2. Click “Process Payroll.”                                                                                                     | User is routed to the payroll processing page.                                                            | User is routed to the payroll processing page.                                                             | Pass            |
|                           | TC-11            | Process Payroll                                    | 1. Input date, employee ID, hours worked, OT, and deductions (SSS, PhilHealth, TIN, Pag-IBIG).<br>2. Click Calculate.<br>3. Click Process Payroll.                    | Payroll processed successfully.                                                                           | Payroll processed successfully.                                                                            | Pass            |
|                           | TC-12            | View Processed Payslip                             | 1. Review inputs.<br>2. Click Process Payroll.<br>3. Click OK in confirmation.                                                                                       | Most recent payslip displayed accurately.                                                                 | Most recent payslip displayed accurately.                                                                  | Pass            |

---

## Summary  
All test cases passed during internal testing. The MotorPH Payroll System meets the specified requirements for login, leave management, employee record handling, and payroll processing.

---
